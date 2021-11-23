package examples.immutableex

object OptionExample extends App {

  def nullify(a: String) = if (a.startsWith("a")) a else null

  val valueOpt = Option(nullify("aaaabbb"))

  val rawValue = valueOpt match {
    case Some(x) => x
    case None => "default value"
  }

  val iterable: Iterable[String] = valueOpt

  iterable.toList
  iterable.map(_ + "c")
  iterable.filter(_.endsWith("b"))
  // ...

}
