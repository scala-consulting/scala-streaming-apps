package ex3_inheritance.b_protected

abstract class Protected {

  protected var v = 0

  protected[ex3_inheritance] def m: Int = v

  private[this] var a: Int = 0

  var overrideme: Int

}

class A(other: Protected) extends Protected {

  def method(): Int = v + other.m

  override var overrideme: Int = 100
}