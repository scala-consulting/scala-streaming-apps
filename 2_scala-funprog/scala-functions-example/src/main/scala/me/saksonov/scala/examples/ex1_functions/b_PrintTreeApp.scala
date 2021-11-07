package me.saksonov.scala.examples.ex1_functions

object PrintTreeApp extends App {

  def printTree(lines: Int): Unit =
    (0 to lines)
      .map(line => " " * (lines - line) + "*" * (line * 2 + 1))
      .foreach(println)

  printTree(100)
}
