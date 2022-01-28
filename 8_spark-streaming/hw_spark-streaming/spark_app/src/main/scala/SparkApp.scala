import org.apache.kafka.common.serialization.{
  IntegerDeserializer,
  LongDeserializer
}
import org.apache.spark.sql.{DataFrame, SparkSession}

// https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
object SparkApp extends App {

  val spark = SparkSession.builder
    .appName("spark-streaming-hw")
    .master(sys.env.getOrElse("spark.master", "local[*]"))
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val brokers = "localhost:9092"
  val topic = "records"

  object KafkaLongDeserializer extends LongDeserializer
  object KafkaIntegerDeserializer extends IntegerDeserializer

  spark.udf.register(
    "deserLong",
    (bytes: Array[Byte]) => KafkaLongDeserializer.deserialize(topic, bytes)
  )
  spark.udf.register(
    "deserInt",
    (bytes: Array[Byte]) => KafkaIntegerDeserializer.deserialize(topic, bytes)
  )

  /** https://spark.apache.org/docs/3.2.0/structured-streaming-kafka-integration.html#kafka-specific-configurations
    *
    * key.deserializer:
    *   Keys are always deserialized as byte arrays with ByteArrayDeserializer.
    *   Use DataFrame operations to explicitly deserialize the keys.
    *
    * value.deserializer:
    *   Values are always deserialized as byte arrays with ByteArrayDeserializer.
    *   Use DataFrame operations to explicitly deserialize the values.
    */
  val records: DataFrame = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", brokers)
    .option("subscribe", topic)
    .load()
    .selectExpr("deserLong(key) AS key_long", "deserInt(value) AS value_int")

  records.printSchema()

  val query = records.writeStream
    .outputMode("update")
    .format("console")
    .start()

  query.awaitTermination()

}
