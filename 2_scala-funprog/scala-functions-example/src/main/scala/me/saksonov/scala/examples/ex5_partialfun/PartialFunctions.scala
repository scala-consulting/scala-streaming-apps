package me.saksonov.scala.examples.ex5_partialfun

object UnsafeFunctionExample extends App {

  val divide42by = (x: Int) => 42 / x

  divide42by(0)
}

object PartialFunctionDefinedExample extends App {

  val divide42by = new PartialFunction[Int, Int] {
    def apply(x: Int): Int = 42 / x

    def isDefinedAt(x: Int): Boolean = x != 0
  }

  if (divide42by.isDefinedAt(0)) divide42by(0)
}

object PartialFunctionCompositionExample extends App {

  val caseDivide42by: PartialFunction[Int, Int] = {
    case x: Int if x != 0 => 42 / x
  }

  val safeDivide42by: PartialFunction[Int, Int] =
    caseDivide42by orElse (identity(_))

  val safeDivide42byPlusOne = safeDivide42by andThen { x => x + 1 }

  println(safeDivide42byPlusOne(0))

  val safeDivide42byCompose = safeDivide42by compose [Int] { _ + 1 }

  caseDivide42by.applyOrElse(0, default = identity[Int])
}
