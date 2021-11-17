package me.saksonov.scala.examples.ex1_functions

object FunctionsHypotExampleApp extends App {
  import scala.math.hypot

  val hypot3 = hypot(3,_)

  val hypotNew = hypot _

  print(hypot3(4)) // hypot(3,4) = 5
}

object FunctionsEqualityApp extends App {

  import scala.math.ceil

  val fCeil = ceil _
  val gCeil: Double => Double = ceil

  println(fCeil == gCeil)

  println(s"f.hashCode: ${fCeil.hashCode()}")
  println(s"g.hashCode: ${gCeil.hashCode()}")

  val pi = 3.14

  println(fCeil(pi) == gCeil(pi))
}

object ListsApp extends App {

  val list = List(1,2,3,4,5,6,7,8,9,10,1)

  val multiple3 = (int: Int) => int % 3 != 0

  val result = list
    .map(v => v * v)  // List(1,4,9...
    .filter(multiple3) // List(1,4,16...
    .takeWhile(_ < 25) // List(1,4,16)
    .reduce(_ * _) // 64

  print(result)
}

object LambdaAsParameterApp extends App {

  val arr = Array(1,2,3,4,5)

  println(arr.map(x => x + 1).mkString("[", ", ", "]"))
  println(arr.map(_ + 1).mkString("[", ", ", "]"))

  //                   ===

  val mapper: Int => Int = (x: Int) => x + 1

  def mapper2(x: Int) = x + 1

  println(arr.map(mapper2).mkString("[", ", ", "]"))
}

object FunctionAsReturnTypeApp extends App {

  def totalPriceForAmount(price: Double): (Int) => (Double) = (amount: Int) => price * amount

  val totalPriceForAmountRtx3060: (Int) => Double = totalPriceForAmount(76999.95)

  print(s"Price of 6 RTX3060 is ${totalPriceForAmountRtx3060(6)}. Happy mining!")
}

