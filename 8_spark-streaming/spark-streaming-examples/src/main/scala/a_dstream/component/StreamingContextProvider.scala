package com.example.spark_streaming
package a_dstream.component

import org.apache.spark.streaming.{Duration, StreamingContext}

trait StreamingContextProvider {
  def streamingContext: StreamingContext
}

trait DefaultStreamingContextProvider extends StreamingContextProvider  {
  this: SparkContextProviderComponent =>

  def batchDuration: Duration

  override lazy val streamingContext: StreamingContext = {
    new StreamingContext(sparkContextProvider.sparkContext, batchDuration)
  }
}

trait StreamingContextProviderComponent {
  def streamingContextProvider: StreamingContextProvider
}
