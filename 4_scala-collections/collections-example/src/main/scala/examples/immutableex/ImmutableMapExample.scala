package examples.immutableex

object ImmutableMapExample extends App {

  // object Map extends MapFactory.Delegate[Map](immutable.Map) {
  val simpleDict = Map(1 -> "Test")

  val dict = Seq(1 -> "Test", 2 -> "Test2", Tuple2(3, "Test"), (4, "Test3")).toMap

  val iterableOfStrings = dict map {
    case (i, s) => s"$i. $s"
  }

  val newDict = iterableOfStrings
    .groupBy(s => s.split("\\.").head.toInt)

  println(newDict)

  val anotherDict = iterableOfStrings.map(s => s.split("\\."))
    .groupMapReduce(_.head)(_.tail.head)(_ + _)

  println(anotherDict)
}
