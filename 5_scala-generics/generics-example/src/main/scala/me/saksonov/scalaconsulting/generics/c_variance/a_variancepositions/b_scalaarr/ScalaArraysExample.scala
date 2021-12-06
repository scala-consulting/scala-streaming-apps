package me.saksonov.scalaconsulting.generics.c_variance.a_variancepositions.b_scalaarr

class Person
class Student extends Person
//class Worker extends Person

object ScalaArraysExample extends App {

  val students = new Array[Student](10)
  //val people: Array[Person] = students
  //people(0) = new Person("Fred") // students(0) уже не Student

  val people = new Array[Person](10)
  //val students: Array[Student] = people
  //people(0) = new Person("Fred") // students(0) перестал быть Student
}
