package me.saksonov.scala.examples.ex2_definitions.ex3_clmeth

object DefinitionsClassMethods extends App {


  class Report(var value: Int,
               coefficient: Double,
               condition: Boolean) {

    def increment(): Unit = value += 1

    def result: Double = if (condition) {
      value * coefficient
    } else {
      value.toDouble
    }

  }


  val report = new Report(0, 0.33, condition = true)

  for {
    _ <- 1 to 10
  } yield report.increment()


  println(report.result)

}
