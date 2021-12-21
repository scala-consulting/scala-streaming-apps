name := "spark-twitter-streams"

version := "0.1"

scalaVersion := "2.12.13"

val sparkVersion = "3.0.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion

libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0"