package me.saksonov.scalaconsulting.generics.a_genclasses

object GenericClassesExample extends App {

  type TableOfContents = List[MyPair[Int, String]]

  val pairTyped: MyPair[Int, String] = new MyPair(42, "String")

  val pairUntyped: MyPair[Any, Any] = new MyPair[Any, Any](42, "Test")
}

class MyPair[First, Second](val first: First, val second: Second)
