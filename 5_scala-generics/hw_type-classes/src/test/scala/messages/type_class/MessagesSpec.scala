package messages.type_class

import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MessagesSpec extends AnyFlatSpec with MockFactory with should.Matchers {

  import language._
  import Messages._

  "hello" should "return hello message in given language" in {
    val language = mock[Language]
    implicit val helloMessageProvider: HelloMessageProvider[Language] = mock[HelloMessageProvider[Language]]

    (helloMessageProvider.message _).expects().returning("Hello").once()

    hello(language) should be("Hello")
  }

  "goodbye" should "return goodbye message in given language" in {
    ???
  }
}