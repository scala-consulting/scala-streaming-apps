import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class RectangleServiceSpec extends AnyFlatSpec with should.Matchers {

  def victim() = new RectangleService

  "RectangleService" should "calculate area for given rectangle data" in {
    val rd = RectangleData(2,2)

    victim().area(rd) should be (4)
  }

  it should "return zero if height is zero" in {
    val rd = RectangleData(0, 5)

    victim().area(rd) should be (0)
  }

  it should "return zero if width is zero" in {
    val rd = RectangleData(5, 0)

    victim().area(rd) should be (0)
  }
}
