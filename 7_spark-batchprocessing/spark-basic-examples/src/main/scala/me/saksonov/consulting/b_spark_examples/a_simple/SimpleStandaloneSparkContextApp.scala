package me.saksonov.consulting.b_spark_examples.a_simple

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SimpleStandaloneSparkContextApp {
  def main(args: Array[String]): Unit = {
    val logFile = "Prismatik.log"

    val sparkConf = new SparkConf()
      .setAppName("SimpleStandaloneSparkContextApp")
      // https://spark.apache.org/docs/latest/configuration.html#application-properties
      // VM Options: -Dspark.master=local[4]
      //.set("spark.master", "local[4]")

    val sc = new SparkContext(sparkConf)

    val someData: RDD[String] = sc.parallelize(Array("a", "b", "a", "b", "a", "b", "a", "b"))

    // https://stackoverflow.com/questions/28981359/why-do-we-need-to-call-cache-or-persist-on-a-rdd
    val logData: RDD[String] = sc.textFile(logFile).cache()

    // Transformations & Actions
    // https://spark.apache.org/docs/latest/rdd-programming-guide.html#transformations
    // https://spark.apache.org/docs/latest/rdd-programming-guide.html#actions
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"============ Lines with a: $numAs, Lines with b: $numBs ============")

    sc.stop()
  }
}
