package ex5_objects.c_apply

class ApplyExample(val x: Int)

object ApplyExample {
  def apply(x: Int) = new ApplyExample(x)
}

object ApplyExampleApp extends App {

  val a = ApplyExample(1)

  println(a.x)
}
