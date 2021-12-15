package me.saksonov.consulting.b_spark_examples.b_cakepattern.sparkapi.b_ds

import me.saksonov.consulting.b_spark_examples.b_cakepattern.provider.{DefaultSparkSessionProvider, SparkSessionProviderComponent}
import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.sql.functions._

object WordCountDatasetExampleApp extends App
  with SparkSessionProviderComponent
  with WordCountDatasetExample {
  override def sparkSessionProvider = new DefaultSparkSessionProvider("WordCountDatasetExampleApp")

  wordCount("Spark-README.md")
}

case class Line(value: String)

case class Word(value: String)

case class WordCount(word: Word, count: Long)

trait WordCountDatasetExample {
  this: SparkSessionProviderComponent =>

  private val sparkSession = sparkSessionProvider.sparkSession

  import sparkSession.implicits._

  def wordCount(path: String): Unit = {

    // DataFrame is simply a type alias of Dataset[Row]

    val textDF: DataFrame = sparkSession.read
      .textFile(path)
      .toDF()

    val linesDS: Dataset[Line] = (textDF: Dataset[Row]).as[Line]

    val wordsDS = linesDS.flatMap(line => line.value.split(" "))
      .filter(_.nonEmpty)
      .as[Word]

    val counts: Dataset[WordCount] =
      wordsDS
        .groupByKey(identity)
        .count() // Row["key", "count(1)"]
//        .withColumnRenamed("key", "word")
//        .withColumnRenamed("count(1)", "count")
//        // Row["word", "count"]
//        .as[WordCount]
        .map { case (word, count) => WordCount(word, count) }

    counts.sort(desc("count")).show(10)
  }

}
