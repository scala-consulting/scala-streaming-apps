package examples.immutableex.c_map

import scala.collection.immutable

object ImmutableMapExample extends App {

  private val tuple: (Int, String) = 1 -> "test"

  // object Map extends MapFactory.Delegate[Map](immutable.Map) {
  val simpleDict = Map(1 -> "Test")

  val dict =
    Seq(1 -> "Test", 2 -> "Test2", Tuple2(3, "Test"), (4, "Test3")).toMap

  val iterableOfStrings: immutable.Iterable[String] = dict map { case (key, value) =>
    s"$key. $value"
  }

  val newDict = iterableOfStrings
    .groupBy(s => s.split("\\.").head.toInt)

  println(newDict)

  val anotherDict = iterableOfStrings
    .map(s => s.split("\\."))
    .groupMapReduce(_.head)(_.tail.head)(_ + _)

  println(anotherDict)
}
