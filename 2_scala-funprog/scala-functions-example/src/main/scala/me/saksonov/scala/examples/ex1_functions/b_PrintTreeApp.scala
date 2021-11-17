package me.saksonov.scala.examples.ex1_functions

object PrintTreeApp extends App {

  private def printTree(numOfLines: Int): Unit =
    lines(numOfLines)
      .foreach(println)

  private def lines(numOfLines: Int): Seq[String] = {
    (0 to numOfLines)
      .map(line => " " * (numOfLines - line) + "*" * (line * 2 + 1))
  }

  printTree(10)
}
