package ex2_overloading

class Overloading {

  def sayHello: (String) => String = (name: String) => s"Hello, $name!"
  def sayHello(name: String): String = s"Hello, $name!"

  def sayHello(name: String, times: Int): String = s"Hello, $name!\n" * times
}

object OverloadingApp extends App {

  val overloading = new Overloading

  overloading.sayHello(name = "test")

  println(overloading.sayHello("Neoflex", 10))
}
