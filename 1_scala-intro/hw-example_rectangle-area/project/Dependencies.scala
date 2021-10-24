import sbt._

object Dependencies {
  // Versions
  lazy val scalaTestVersion = "3.2.10"

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
}
