package me.saksonov.consulting.b_spark_examples.b_cakepattern.provider

import org.apache.spark.{SparkConf, SparkContext}

trait SparkContextProvider {
  def sparkContext: SparkContext
}

class DefaultSparkContextProvider(appName: String) extends SparkContextProvider {
  override lazy val sparkContext: SparkContext = {
    val sparkConf = new SparkConf().setAppName(appName)

    new SparkContext(sparkConf)
  }
}

trait SparkContextProviderComponent {
  def sparkContextProvider: SparkContextProvider
}
