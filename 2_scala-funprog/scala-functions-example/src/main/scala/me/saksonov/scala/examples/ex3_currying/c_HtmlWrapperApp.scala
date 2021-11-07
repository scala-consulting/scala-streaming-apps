package me.saksonov.scala.examples.ex3_currying

object HtmlWrapper {
  def wrap(prefix: String, suffix: String)(html: String): String = {
    prefix + html + suffix
  }
}

object HtmlWrapperApp extends App {

  import HtmlWrapper._

  val helloWorld = "Hello, World"
  val wrappedHelloWorld: String = wrap("<div>", "</div>")(helloWorld)

  val wrapWithDiv: String => String = wrap("<div>", "</div>") // PAF

  print(s"${wrapWithDiv(helloWorld)} == $wrappedHelloWorld")

}
