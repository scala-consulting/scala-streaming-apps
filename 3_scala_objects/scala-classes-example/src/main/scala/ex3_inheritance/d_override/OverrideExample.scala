package ex3_inheritance.d_override

abstract class SuperOverrideExample {
  val c: Int = 0
  def b: String = a

  var a: String

}

class OverrideExample extends SuperOverrideExample {
  override val c: Int = 1000
  override val b: String = "Test"
  override var a: String = "Test2"

  override def toString: String = s"c=$c,b=$b,a=$a"
}

object OverrideExampleApp extends App {
  val ex = new OverrideExample

  println(ex)
}
