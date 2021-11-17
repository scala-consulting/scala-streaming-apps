package me.saksonov.scala.examples.ex3_currying

object Mul {

  def mul(x: Int, y: Int): Int = x * y

  def mulF(x: Int): Int => Int = (y: Int) => x * y

  def mulC(x: Int)(y: Int): Int = x * y

}

object CurryingExamplesApp extends App {

  import Mul._

  val mul1 = mul(1, _)
  val r1 = mul1(2) // 2

  val mul2: Int => Int = mulF(2)
  val r2 = mul2(2) // 4

  val mul3 = mulC(3)(_)
  val r3 = mul3(2) // 6

  print(s"r1=$r1 r2=$r2 r3=$r3")
}


