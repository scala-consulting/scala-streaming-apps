package me.saksonov.scala.examples.ex1_functions

object SortExampleApp extends App {

  print(
    "the quick brown fox jumps over the lazy dog"
      .split(" ") // Array
      .sortWith(_.length > _.length)
      .mkString(" ") // String
  )

}
