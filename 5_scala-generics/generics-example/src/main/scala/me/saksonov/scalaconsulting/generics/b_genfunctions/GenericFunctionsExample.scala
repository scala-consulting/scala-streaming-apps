package me.saksonov.scalaconsulting.generics.b_genfunctions

object GenericFunctionsExample extends App {

  def getMidElem[T](arr: Array[T]): T = arr(arr.length / 2)

  val result: String = getMidElem(Array("Hello", ", ", "Neoflex", "!")) // getMidElem[String]


  val getMidElemNoType: Array[Nothing] => Nothing = getMidElem _ // Array[Nothing] :(

  val getMidElemString = getMidElem[String] _
}

object ExceptionNothingExampleApp extends App {

  def doNothing(): Nothing = {
    throw new IllegalStateException("TODO")
  }
}