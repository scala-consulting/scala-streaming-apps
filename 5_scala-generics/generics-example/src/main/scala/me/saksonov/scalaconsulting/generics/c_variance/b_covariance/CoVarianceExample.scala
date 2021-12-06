package me.saksonov.scalaconsulting.generics.c_variance.b_covariance

case class InvPair[T](f: T, s: T)
case class CovPair[+T](f: T, s: T)

class Person(val age: Int, val name: String)
class Student(age: Int, name: String, val phone: String) extends Person(age, name)

object VarianceExample extends App {

  def makeFriends(p: InvPair[Person]) = ???
  def makeFriends(cp: CovPair[Person]) = ???

  val st = new Student(18, "John Doe", phone = "123")
  val st2 = new Student(20, "Example", phone = "456")

  val pair: InvPair[Student] = InvPair(st, st2)
  //makeFriends(pair) // - doesn't work :(
  //makeFriends(InvPair(st: Person, st2))

  val covPair = CovPair(st, st2)
  makeFriends(covPair)
}
