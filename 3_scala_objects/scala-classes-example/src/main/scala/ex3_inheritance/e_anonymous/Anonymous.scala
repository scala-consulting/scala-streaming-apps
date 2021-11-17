package ex3_inheritance.e_anonymous

abstract class SuperClass {
  def m: String
}

object AnonymousApp extends App {

  val sc = new SuperClass {
    override def m: String = "Anonymous"
  }

  println(sc.m)
}
