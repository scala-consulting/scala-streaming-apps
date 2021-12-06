package examples.immutableex.a_seq

object IndexedSeqExample extends App {

  val range: IndexedSeq[Int] = 1 to 10 // Range(1,10)

  val vector: IndexedSeq[String] = Vector("Hello", ", ", "World!")

  // fast index access:

  vector(3) // =)

}
