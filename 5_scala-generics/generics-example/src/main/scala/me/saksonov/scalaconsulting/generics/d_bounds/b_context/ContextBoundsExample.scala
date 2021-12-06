package me.saksonov.scalaconsulting.generics.d_bounds.b_context

class OrdPair[T : Ordering](val first: T, val second: T) {

  def smaller: T =
    if (implicitly[Ordering[T]].compare(first, second) < 0) first else second
}

object ContextBoundsExample extends App {

  implicit val myOrdering: Ordering[Int]  = Ordering.fromLessThan[Int]((l,r) => l > r)

  val intPair: OrdPair[Int] = new OrdPair(42, 100)


  println(intPair.smaller)

  // val anyPair: OrdPair[Any] = new OrdPair[Any](new Object, new Object)
}

