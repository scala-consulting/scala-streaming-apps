package me.saksonov.scalaconsulting.generics.d_bounds.a_type

class UPair[T <: Comparable[T]](val f: T, val s: T) {
  // upper bound - type T must be subtype of Comparable[T]

  def smaller: T = if (f.compareTo(s) < 0) f else s
}

class LPair[T](val f: T, val s: T) {
  // lower bound - type R must be supertype of T

  def replaceFirst[R >: T](newF: R) = new LPair[R](newF, s)

  // type of pair is relaxed - Pair[Student] -> Pair[Person]
}

object TypeBoundsExamples extends App {

  val upperBoundPair = new UPair("Fred", "Brooks")

  println(upperBoundPair.smaller)

  val lowerBoundPair: LPair[Int] = new LPair(2, 3)

  val newLowerBoundPair: LPair[AnyVal] = lowerBoundPair.replaceFirst(3.0)
}
