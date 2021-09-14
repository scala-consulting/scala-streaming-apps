package me.saksonov.scala.examples.ex1_helloworld.ex3_stdin

object StdInHelloWorld extends App {

  import scala.io.StdIn

  println("Line:")

  val line = StdIn.readLine()

  println(s"StdIn: $line")
}
