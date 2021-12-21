package com.example.spark_streaming
package a_dstream.component

import org.apache.spark.{SparkConf, SparkContext}

trait SparkContextProvider {
  def sparkContext: SparkContext
}

class DefaultSparkContextProvider(appName: String) extends SparkContextProvider {
  override lazy val sparkContext: SparkContext = {
    val sparkConf = new SparkConf()
      .setAppName(appName)
      .setMaster(sys.env.getOrElse("spark.master", "local[*]"))

    new SparkContext(sparkConf)
  }
}

trait SparkContextProviderComponent {
  def sparkContextProvider: SparkContextProvider
}
