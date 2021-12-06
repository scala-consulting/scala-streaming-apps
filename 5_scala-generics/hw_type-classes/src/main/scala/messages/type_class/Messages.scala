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
object HelloMessageProvider {
  implicit val englishHelloMessageProvider: HelloMessageProvider[EnglishLanguage.type] =
    new EnglishHelloMessageProvider
}

object Messages {
  def hello[L <: Language](l: Language)(implicit hmp: HelloMessageProvider[L]): String =
    hmp.message

  def goodbye: String = ???
}

object TypeClassMessagesApp extends App {
  import Messages._

  println(hello(EnglishLanguage))
}
