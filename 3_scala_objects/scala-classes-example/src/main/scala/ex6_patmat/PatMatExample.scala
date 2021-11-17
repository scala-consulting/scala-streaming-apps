package ex6_patmat

trait Amount
case class Dollar(amount: BigDecimal) extends Amount
case class Currency(amount: BigDecimal, unit: String) extends Amount

object DataContainerApp extends App {

//  val c1 = Currency(10, "RUB")
//  val c2 = Currency(10, "RUB")
//
//  println(c1 == c2)
//  println(c1.equals(c2))
//
//  println(c1)

  val amounts: List[Any] = List(Dollar(10), Currency(100, "RUB"), "String")

  for {
    amount <- amounts
  } {
    amount match {
      case Dollar(amount) =>
        println("$$$ " + amount)

      case obj@Currency(amount, unit) =>
        println(s"$amount $unit (obj: $obj)")

      case _ =>
    }
  }


}
