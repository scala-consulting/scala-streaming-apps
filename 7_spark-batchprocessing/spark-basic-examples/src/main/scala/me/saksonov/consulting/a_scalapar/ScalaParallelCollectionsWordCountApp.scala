package me.saksonov.consulting.a_scalapar

import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.ForkJoinTaskSupport
import scala.collection.parallel.immutable._
import scala.io.Source

// wc -w Spark-README.md Prismatik.log
// 1051 Spark-README.md
// 2214 Prismatik.log
// 3265 total
object ScalaParallelCollectionsWordCountApp extends App {

  val source = Source.fromFile("Spark-README.md")
  val forkJoinPool = new java.util.concurrent.ForkJoinPool(4)

  val result: Seq[(String, Int)] =
    try {

      val parList: ParSeq[String] = source
        .getLines()
        .toList
        .flatMap(line => line.split(" "))
        .par

      // https://docs.scala-lang.org/overviews/parallel-collections/configuration.html
      parList.tasksupport = new ForkJoinTaskSupport(forkJoinPool)

      val parMap: ParMap[String, ParSeq[String]] = parList
        .filter(_.nonEmpty)
        .groupBy(identity)

      parMap // the -> List(the, the, the, the, ...)
        .mapValues(_.size)
        .toList: List[(String, Int)]
    } finally {
      source.close()
      forkJoinPool.shutdown()
    }

  result
    .sortBy({ case (_, count) => count })(Ordering[Int].reverse)
    .take(15)
    .foreach { case (word, count) =>
      println(s"$word : $count")
    }
}
