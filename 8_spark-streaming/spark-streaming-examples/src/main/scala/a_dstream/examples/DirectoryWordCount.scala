package com.example.spark_streaming
package a_dstream.examples

import a_dstream.component._

import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Duration, Seconds}

object DirectoryWordCountApp extends App
  with DirectoryWordCountExample
  with StreamingContextProviderComponent
  with SparkContextProviderComponent {

  private lazy val defaultSparkContextProvider = new DefaultSparkContextProvider("DirectoryWordCountApp")

  override def sparkContextProvider: SparkContextProvider = defaultSparkContextProvider

  override def streamingContextProvider: StreamingContextProvider =
    new DefaultStreamingContextProvider with SparkContextProviderComponent {
      override def batchDuration: Duration = Seconds(10)

      override def sparkContextProvider: SparkContextProvider = defaultSparkContextProvider
    }

  runDirectoryWordCountExample("data")
}

trait DirectoryWordCountExample {
  this: StreamingContextProviderComponent =>

  private lazy val streamingContext = streamingContextProvider.streamingContext

  def runDirectoryWordCountExample(dataDirectory: String): Unit = {
    val lines: DStream[String] = streamingContext.textFileStream(dataDirectory) // file modification time must be new!!!

    val words = lines.flatMap(_.split(" "))

    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)

    wordCounts.print()

    streamingContext.start()

    streamingContext.awaitTermination()
  }
}
