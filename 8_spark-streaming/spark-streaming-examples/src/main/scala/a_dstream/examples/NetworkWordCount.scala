package com.example.spark_streaming
package a_dstream.examples

import a_dstream.component._

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Duration, Seconds}

object NetworkWordCountApp extends App
  with NetworkWordCountExample
  with StreamingContextProviderComponent
  with SparkContextProviderComponent {
  private lazy val defaultSparkContextProvider = new DefaultSparkContextProvider("NetworkWordCountApp")

  override def sparkContextProvider: SparkContextProvider = defaultSparkContextProvider

  override def streamingContextProvider: StreamingContextProvider =
    new DefaultStreamingContextProvider with SparkContextProviderComponent {
      override def batchDuration: Duration = Seconds(5)

      override def sparkContextProvider: SparkContextProvider = defaultSparkContextProvider
    }

  runNetworkWordCountExample("localhost", 9999)
}

trait NetworkWordCountExample {
  this: StreamingContextProviderComponent =>

  private lazy val streamingContext = streamingContextProvider.streamingContext

  def runNetworkWordCountExample(hostname: String, port: Int): Unit = {

    // Spark Streaming Programming Guide:
    //    https://spark.apache.org/docs/latest/streaming-programming-guide.html#data-serialization
    // Spark Tuning Guide:
    //    https://spark.apache.org/docs/latest/tuning.html#data-serialization

    // Input data:
    //  By default, the input data received through Receivers is stored in the executors’ memory
    //  with StorageLevel.MEMORY_AND_DISK_SER_2.
    //  That is, the data is serialized into bytes to reduce GC overheads, and replicated
    //  for tolerating executor failures. Also, the data is kept first in memory,
    //  and spilled over to disk only if the memory is insufficient to hold all of
    //  the input data necessary for the streaming computation.
    //  This serialization obviously has overheads – the receiver must deserialize
    //  the received data and re-serialize it using Spark’s serialization format.
    val lines: DStream[String] = streamingContext.socketTextStream(hostname, port, storageLevel = StorageLevel.MEMORY_AND_DISK_SER_2)

    val words = lines.flatMap(_.split(" "))

    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)

    wordCounts.print()

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}
