package examples.immutableex.a_seq

object ImmutableSeqExample extends App {

  // object Seq extends SeqFactory.Delegate[Seq](List)
  val seq: Seq[Int] = Seq(1, 2, 3)

  println(seq.isInstanceOf[List[Int]]) // true

  // slow index access:
  println(seq(1)) // =( .next.next.next

  val seq2 = seq :+ 1 // appended
  val seq2unsugared = seq.:+(1)

  val seq3 = 1 +: seq // prepended
  val seq3unsugared = seq.+:(1)

  // - :-ending operators are right associative
  //
  // COLon goes on the COLlection side  !!!

  val seq4: Seq[Int] = Seq(4, 5, 6)

  val concat = seq ++ seq4
}
