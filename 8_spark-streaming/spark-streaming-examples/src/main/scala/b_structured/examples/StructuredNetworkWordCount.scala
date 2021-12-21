package com.example.spark_streaming
package b_structured.examples

import b_structured.component._

import org.apache.spark.sql.streaming.Trigger.ProcessingTime
import org.apache.spark.sql.streaming.{OutputMode, StreamingQuery}
import org.apache.spark.sql.{DataFrame, Dataset}

import scala.concurrent.duration.DurationInt

object StructuredNetworkWordCountApp extends App
  with StructuredNetworkWordCountExample
  with SparkSessionProviderComponent {
  override def sparkSessionProvider = new DefaultSparkSessionProvider("StructuredNetworkWordCountApp")

  runStructuredNetworkWordCountExample("localhost", 9999)
}

trait StructuredNetworkWordCountExample {
  this: SparkSessionProviderComponent =>

  private lazy val spark = sparkSessionProvider.sparkSession

  import spark.implicits._

  def runStructuredNetworkWordCountExample(host: String, port: Int): Unit = {
    val lines: DataFrame = spark.readStream
      .format("socket")
      .option("host", host)
      .option("port", port)
      .load()

    // Split the lines into words
    val words: Dataset[String] = lines.as[String].flatMap(_.split(" "))

    // Structured Stream = Endless Table :)

    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    // Start running the query that prints the running counts to the console
    val query: StreamingQuery = wordCounts.writeStream
      .outputMode(OutputMode.Update)
      .format("console")
      // https://spark.apache.org/docs/latest/streaming-programming-guide.html#checkpointing
      //.option("checkpointLocation", "path/to/HDFS/dir")

      // The default value is ProcessingTime(0) and it will run the query as fast as possible.
      .trigger(trigger = ProcessingTime(2.seconds))
      .start()

    query.awaitTermination()
  }
}
