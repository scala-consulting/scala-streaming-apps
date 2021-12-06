package me.saksonov.scalaconsulting.generics.e_implicits

import scala.io.BufferedSource
import scala.util.{Failure, Success}

object PimpMyLibraryPatternExampleApp extends App {

  import scala.io.Source
  import scala.util.{Try, Using}

  implicit class RichFileOps(val file: java.io.File) {
    def readContent: Try[String] = Using(Source.fromFile(file.getPath))((bs: BufferedSource) => bs.mkString)
  }

  private val file = new java.io.File("README")

  file.readContent match {
    case Success(content) => println(content)
    case Failure(exception) => println(s"Error: ${exception.getMessage}")
  }
}
