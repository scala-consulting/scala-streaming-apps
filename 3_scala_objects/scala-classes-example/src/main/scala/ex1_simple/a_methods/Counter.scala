package ex1_simple.a_methods

class Counter {

  private var value = 0

  def increment(): Unit = {
    value += 1
  }

  def current: Int = value
}

object CounterApp extends App {
  var counter = new Counter
  var counter2 = new Counter()

  counter.increment()

  println(counter.current) // println(counter.current())

}