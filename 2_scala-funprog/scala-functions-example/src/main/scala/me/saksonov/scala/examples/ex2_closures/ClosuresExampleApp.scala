package me.saksonov.scala.examples.ex2_closures

import scala.io.StdIn

object ClosuresExampleApp extends App {

  print("Enter X: ")

  val x = StdIn.readInt()

  val mulX: (Int, Int) => Int = {
    x * _ * _
  }

  val mul3X = mulX(3, _)

  println(s"X * 3 * 5 = ${mul3X(5)}")
}
