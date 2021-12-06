package me.saksonov.scalaconsulting.generics.e_implicits

import scala.jdk.CollectionConverters
import scala.language.implicitConversions

case class SimpleFraction(numerator: Int, denominator: Int) {

  def *(other: SimpleFraction): SimpleFraction =
    SimpleFraction(numerator * other.numerator, denominator * other.denominator)
}

object ImplicitsExampleApp extends App {

  implicit def integer2SimpleFraction(integer: Int): SimpleFraction = SimpleFraction(integer, 1)

  val result = 3 * SimpleFraction(4, 5)

  println(result)
}

object JavaCollectionsUtilityApp extends App {
  import CollectionConverters._

  val javaList = new java.util.ArrayList[Int]()

  javaList.asScala
}
