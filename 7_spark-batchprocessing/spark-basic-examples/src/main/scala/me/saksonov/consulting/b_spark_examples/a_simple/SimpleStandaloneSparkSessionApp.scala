package me.saksonov.consulting.b_spark_examples.a_simple

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SimpleStandaloneSparkSessionApp {
  def main(args: Array[String]): Unit = {
    val logFile = "Prismatik.log"

    val spark = SparkSession.builder
      // https://spark.apache.org/docs/latest/configuration.html
      // VM Options: -Dspark.master=local[4]
      //.config("spark.master", "local[4]")
      .getOrCreate()

    import spark.implicits._

    val someDataFrame: DataFrame = Seq("a", "b", "a", "b", "a", "b", "a", "b").toDF()
    val someDataSet: Dataset[String] = Seq("a", "b", "a", "b", "a", "b", "a", "b").toDS()

    // https://stackoverflow.com/questions/28981359/why-do-we-need-to-call-cache-or-persist-on-a-rdd
    val logData: Dataset[String] = spark.read.textFile(logFile).cache()

    // Transformations & Actions
    // https://spark.apache.org/docs/latest/rdd-programming-guide.html#transformations
    // https://spark.apache.org/docs/latest/rdd-programming-guide.html#actions
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"============ Lines with a: $numAs, Lines with b: $numBs ============")

    spark.stop()
  }
}
