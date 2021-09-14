package me.saksonov.scala.examples.ex2_definitions.ex2_func

object DefinitionsFunc {

  def plusM(x: Int, y: Int): Int = {
    1 + 1
    return x + y
  }

  val resultM = plusM(1, 1) // 2

  // ===

  val plusF: (Int, Int) => Int = (x, y) => x + y

  val resultF = plusF(1, 1)

  // ===

  def applyF(f: (Int, Int) => Int)(x: Int, y: Int) = f(x, y)

  val resultApplyF = applyF(plusF)(1, 1) // 2

  val resultApplyM = applyF(plusM(_, _))(1, 1)
}
