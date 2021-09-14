package me.saksonov.scala.examples.ex1_helloworld.ex2_app

object AppHelloWorld extends App {
  println(s"Hello, ${args.headOption.getOrElse("World")}!")
}
