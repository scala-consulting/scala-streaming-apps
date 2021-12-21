name := "spark-streaming-examples"

version := "0.1"

scalaVersion := "2.13.7"

idePackagePrefix := Some("com.example.spark_streaming")

val sparkVersion = "3.2.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion
