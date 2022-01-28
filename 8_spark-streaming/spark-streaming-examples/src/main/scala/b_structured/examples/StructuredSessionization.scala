package com.example.spark_streaming
package b_structured.examples

import b_structured.component._

import org.apache.spark.sql.streaming.{GroupState, GroupStateTimeout}
import org.apache.spark.sql.{Dataset, Row}

import java.sql.Timestamp

// Input: Stream[Event]
case class Event(sessionId: String, timestamp: Timestamp)

case class SessionInfo(numEvents: Int, startTimestampMs: Long, endTimestampMs: Long) {
  def durationMs: Long = endTimestampMs - startTimestampMs
}

// Output: Stream[SessionUpdate]
case class SessionUpdate(id: String, durationMs: Long, numEvents: Int, expired: Boolean)

trait StructuredSessionizationExample {
  this: SparkSessionProviderComponent =>

  private lazy val sparkSession = sparkSessionProvider.sparkSession

  import sparkSession.implicits._

  def runStructuredSessionizationExample(host: String, port: Int): Unit = {

    val lines: Dataset[Row] = sparkSession.readStream
      .format("socket")
      .option("host", host)
      .option("port", port)
      .option("includeTimestamp", value = true) // DataFrame with 2 columns: Word+Timestamp
      .load()

    val events = lines
      .as[(String, Timestamp)]
      .flatMap { case (line, timestamp) =>
        line.split(" ").map(word => (word, timestamp))
      }
      .as[Event]

    // Sessionize the events. Track number of events, start and end timestamps of session,
    // and report session updates.
    val sessionUpdates = events
      .groupByKey(event => event.sessionId)
      .mapGroupsWithState[SessionInfo, SessionUpdate](timeoutConf = GroupStateTimeout.ProcessingTimeTimeout) {
        // For a static batch Dataset, the function will be invoked once per group. For a streaming Dataset,
        // the function will be invoked for each group repeatedly in every trigger,
        // and updates to each group's state will be saved across invocations.

        case (sessionId: String, events: Iterator[Event], state: GroupState[SessionInfo]) =>

          // If timed out, then remove session and send final update
          if (!state.hasTimedOut) {
            // Update start and end timestamps in session
            val timestamps = events.map(_.timestamp.getTime).toSeq
            val updatedSession = if (!state.exists) {
              SessionInfo(timestamps.size, timestamps.min, timestamps.max)
            } else {
              val oldSession = state.get

              SessionInfo(
                oldSession.numEvents + timestamps.size,
                math.min(oldSession.startTimestampMs, timestamps.min),
                math.max(oldSession.endTimestampMs, timestamps.max))
            }
            state.update(updatedSession)

            // Set timeout such that the session will be expired if no data received for 10 seconds
            state.setTimeoutDuration("10 seconds")
            SessionUpdate(sessionId, state.get.durationMs, state.get.numEvents, expired = false)
          } else {
            val finalUpdate =
              SessionUpdate(sessionId, state.get.durationMs, state.get.numEvents, expired = true)
            state.remove()
            finalUpdate
          }
      }

    // Start running the query that prints the session updates to the console
    val query = sessionUpdates
      .writeStream
      .outputMode("update")
      .format("console")
      .start()

    query.awaitTermination()
  }

}
