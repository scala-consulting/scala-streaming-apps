package me.saksonov.scala.examples.ex2_definitions.ex4_generics

object DefinitionsGenerics extends App {

  def logging[T](v: T): T = {
    println(v)
    return v
  }

  val str: String = logging("test")

  val num: Int = logging(3)
}
