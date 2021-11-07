package me.saksonov.scala.examples.ex3_currying

// Pimp My Library
object RichCorrespondsOps {

  implicit class RichCorrespondsSeq(val seq: Seq[String]) {

    def correspondsByLength(that: Seq[Int]): Boolean = seq.corresponds(that)(_.length == _)
  }
}

object CorrespondsExampleApp extends App {

  import RichCorrespondsOps._

  val strings = Seq("lazy", "dog")
  val integers = Seq(4, 3)

  print(s"${strings.correspondsByLength(integers)}")
}
