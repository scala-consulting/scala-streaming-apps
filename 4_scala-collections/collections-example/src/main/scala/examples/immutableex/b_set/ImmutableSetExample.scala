package examples.immutableex.b_set

object ImmutableSetExample extends App {

  // object Set extends IterableFactory.Delegate[Set](immutable.Set)
  val set = Set("test", "test2", "test3")

  val set2 = set map (_ + 1)

  println(set2)

}
