package messages.type_class

import language._

trait MessageProvider {
  def message: String
}
trait HelloMessageProvider[L <: Language] extends MessageProvider {
  override def message: String
}
final class EnglishHelloMessageProvider extends HelloMessageProvider[EnglishLanguage.type] {
  override def message: String = "Hello"
}
final class RussianHelloMessageProvider extends HelloMessageProvider[RussianLanguage.type] {
  override def message: String = "Привет"
}
object HelloMessageProvider {
  implicit val englishHelloMessageProvider: HelloMessageProvider[EnglishLanguage.type] =
    new EnglishHelloMessageProvider
  implicit val russianHelloMessageProvider: HelloMessageProvider[RussianLanguage.type] =
    new RussianHelloMessageProvider
}

object Messages {
  def hello[L <: Language](l: L)(implicit hmp: HelloMessageProvider[L]): String =
    hmp.message

  def goodbye: String = ???
}

object TypeClassMessagesApp extends App {
  import Messages._

  println(hello(EnglishLanguage))
}
