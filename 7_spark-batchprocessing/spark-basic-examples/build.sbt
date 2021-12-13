name := "spark-basic-examples"

version := "0.1"

scalaVersion := "2.13.7"

val sparkVersion = "3.2.0"

// https://github.com/scala/scala-parallel-collections
libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

// sbt package
// spark-submit --class "me.saksonov.consulting.spark_examples.simple.SimpleStandaloneSparkContextApp" --master local[4] target/scala-2.12/spark-basic-examples_2.12-0.1.jar
