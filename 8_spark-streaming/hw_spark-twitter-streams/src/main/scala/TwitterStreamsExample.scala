import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status
import twitter4j.auth.{Authorization, OAuthAuthorization}
import twitter4j.conf.ConfigurationBuilder

object TwitterStreamsExampleApp extends App
  with TwitterStreamsExample
  with SparkSessionProviderComponent {

  override def sparkSessionProvider: SparkSessionProvider = new DefaultSparkSessionProvider("TwitterStreamsExampleApp")

  runTwitterStreamsExample()
}

trait TwitterStreamsExample {
  this: SparkSessionProviderComponent =>

  private lazy val sparkSession = sparkSessionProvider.sparkSession

  import sparkSession.implicits._
  import org.apache.spark.sql.streaming._

  // https://github.com/stefanobaghino/spark-twitter-stream-example/blob/master/src/main/scala/me/baghino/spark/streaming/twitter/example/TwitterSentimentScore.scala
  def runTwitterStreamsExample(): Unit = {
    val streamingContext = new StreamingContext(sparkSession.sparkContext, Seconds(5))

    val tweets: ReceiverInputDStream[Status] =
      TwitterUtils.createStream(streamingContext, twitterAuth= None, filters = Nil)

    tweets.filter(tweet => !tweet.isRetweet)
      .map(_.getText)
      .print()

    streamingContext.start()
  }
}
