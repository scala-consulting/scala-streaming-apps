import org.apache.spark.sql.SparkSession

trait SparkSessionProvider {
  def sparkSession: SparkSession
}

class DefaultSparkSessionProvider(appName: String, println: Any => Unit = Console.println) extends SparkSessionProvider {
  override lazy val sparkSession: SparkSession = {
    val sparkSession = SparkSession.builder
      .appName(appName)
      .getOrCreate()

    sparkSession.sparkContext.setLogLevel("ERROR")

    printSparkConf(sparkSession)

    sparkSession
  }

  private def printSparkConf(sparkSession: SparkSession): Unit = {
    println(s"DefaultSparkSessionProvider($appName)")

    sparkSession.sparkContext.getConf.getAll.foreach {
      case (key, value) => println(s"\t$key=$value")
    }
  }
}

trait SparkSessionProviderComponent {
  def sparkSessionProvider: SparkSessionProvider
}
