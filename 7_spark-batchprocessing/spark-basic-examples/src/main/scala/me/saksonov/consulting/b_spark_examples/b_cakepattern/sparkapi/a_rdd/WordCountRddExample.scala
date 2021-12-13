package me.saksonov.consulting.b_spark_examples.b_cakepattern.sparkapi.a_rdd

import me.saksonov.consulting.b_spark_examples.b_cakepattern.provider.{DefaultSparkContextProvider, SparkContextProviderComponent}
import org.apache.spark.rdd.RDD

object WordCountRddExampleApp extends App
  with SparkContextProviderComponent
  with WordCountRddExample {

  override def sparkContextProvider = new DefaultSparkContextProvider("WordCountRddExampleApp")

  wordCount("Spark-README.md")
}

trait WordCountRddExample {
  this: SparkContextProviderComponent =>

  private val sparkContext = sparkContextProvider.sparkContext

  def wordCount(path: String): Unit = {
    val textFile: RDD[String] = sparkContext.textFile(path)

    val counts: RDD[(String, Int)] = textFile.flatMap(line => line.split(" "))
      .filter(_.nonEmpty)
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.sortBy({ case (word, count) => count }, ascending = false)
      .take(10)
      .foreach { case (word, count) =>
        println(s"========== '$word' : $count")
      }
  }

}
