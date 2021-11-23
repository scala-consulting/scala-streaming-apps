package examples.immutableex

object ImmutableSetExample extends App {

  // object Set extends IterableFactory.Delegate[Set](immutable.Set)
  val set = Set(1,2,3)

  val set2 = set map (_ + 1)

  println(set2)

}
