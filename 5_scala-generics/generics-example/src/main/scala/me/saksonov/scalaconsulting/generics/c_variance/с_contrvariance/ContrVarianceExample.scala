package me.saksonov.scalaconsulting.generics.c_variance.с_contrvariance

trait Friend[-T] {
  def befriend(someone: T) = ???
}
class Person extends Friend[Person]
class Student extends Person

object ContrVarianceExample extends App {

  def makeFriends(friend: Friend[Student], student: Student): Unit = { friend.befriend(student) }

  val fred: Friend[Student] = new Person
  val susan = new Student

  makeFriends(fred, susan)
}

object FunctionExampleApp extends App {

  val function: Int => String = (integer: Int) => integer.toString

  // function: Function1[Int, String]

  def friendsOf(students: Array[Student])(findFriend: Function1[Student, Person]): Array[Person] =
    for (student <- students) yield findFriend(student)

  def findStudent(person: Person) : Student = ???

  friendsOf(Array())(findStudent: Person => Student)
}
