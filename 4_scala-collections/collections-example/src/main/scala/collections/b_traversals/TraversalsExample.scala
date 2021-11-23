package collections.b_traversals

import scala.collection.SortedMap
import scala.collection.immutable.{BitSet, SortedSet, TreeMap}

object TraversalsSetExample extends App {

  // same-result-type

  val bits = BitSet(1, 2, 3)
  val newBits: BitSet = bits map (_ * 2)

  // or at least max-specialized type if same type is not possible

  val floatSet: SortedSet[Float] = bits map (_.toFloat)
  val stringSet: Set[String] = bits map (_.toString)
}

object TraversalsMapExample extends App {
  val dict: SortedMap[String, Int] = TreeMap("a" -> 1, "b" -> 2)

  // normal overloading of "map" method in Scala 2.13 instead of CanBuildFrom in Scala 2.12

  val mirroredMap: SortedMap[Int, String] = dict map { case (k, v) => (v, k) }

  val keys: Iterable[String] = dict map { case (k, _) => k }
  val values: Iterable[Int] = dict map { case (_, v) => v }

  // collect uses IterableFactory

  val multiplOf3StartingWithSome: Iterable[String] = dict.collect {
    case (k, v) if v % 3 == 0 && k.startsWith("some") => s"$k=$v"
  }
}
