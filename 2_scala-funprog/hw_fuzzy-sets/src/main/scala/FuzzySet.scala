object FuzzySet {
  class Universe[T](val values: Set[T])
}

class FuzzySet[T](m: T => Double) {
  import FuzzySet.Universe

  def isEmpty(implicit universe: Universe[T]): Boolean =
    universe.values.forall(m(_) == 0.0)

  def equalTo(that: FuzzySet[T])(implicit universe: Universe[T]): Boolean = ???

  def contains(value: T): Double = ???

  def union(that: FuzzySet[T]): FuzzySet[T] = ???

  def intersect(that: FuzzySet[T]): FuzzySet[T] = ???

  def complement(implicit universe: Universe[T]): FuzzySet[T] = ???

}

object FuzzySetApp extends App {
  import FuzzySet.Universe

  implicit val fuzzySetUniverse: Universe[Int] = new Universe(Set.from(1 to 10))

  val emptyFuzzySet = new FuzzySet[Int](_ => 0.0)
  println(emptyFuzzySet.isEmpty)

  val someNonEmptyFuzzySet = new FuzzySet[Int]({
    case 1 => 0.5
    case 2 => 0.75
    case 3 => 1
    case _ => 0.0
  })
  println(someNonEmptyFuzzySet.isEmpty)
}