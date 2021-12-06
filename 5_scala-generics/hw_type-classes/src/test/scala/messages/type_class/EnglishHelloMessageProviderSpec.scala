package messages.type_class

import org.scalatest.flatspec._
import org.scalatest.matchers._

class EnglishHelloMessageProviderSpec extends AnyFlatSpec with should.Matchers {

  private val englishHelloMessageProvider = new EnglishHelloMessageProvider

  "EnglishHelloMessageProvider" should "provide hello message" in {
    englishHelloMessageProvider.message should be ("Hello")
  }
}
