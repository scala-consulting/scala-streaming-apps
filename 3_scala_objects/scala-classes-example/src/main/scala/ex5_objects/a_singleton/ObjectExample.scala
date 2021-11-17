package ex5_objects.a_singleton

import ex4_traits.a_traits.ConsoleLogger

object Account extends ConsoleLogger {

  private var lastNumber = 0

  def newUniqueNumber(): Int = { lastNumber += 1; log(lastNumber.toString); lastNumber }

}

class Account {
  import Account._

  val id: Int = newUniqueNumber()

  private var balance: Double = 0.0

  def deposit(amount: Double): Unit = { balance += amount }
}

object AccountApp extends {

  val acc = new Account

  acc.deposit(1.0)
}
