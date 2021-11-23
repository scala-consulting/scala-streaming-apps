package examples.immutableex

object ImmutableSeqExample extends App {

  // object Seq extends SeqFactory.Delegate[Seq](List)
  val seq: Seq[Int] = Seq(1,2,3)

  println(seq.isInstanceOf[List[Int]]) // true

  // slow index access:
  seq(3) // =( .next.next.next

  val seq2 = seq :+ 1 // appended
  val seq3 = 1 +: seq // prepended
  // - :-ending operators are right associative
  //
  // COLon goes on the COLlection side  !!!

  val seq4: Seq[Int] = Seq(4,5,6)

  val concat = seq ++ seq4
}
