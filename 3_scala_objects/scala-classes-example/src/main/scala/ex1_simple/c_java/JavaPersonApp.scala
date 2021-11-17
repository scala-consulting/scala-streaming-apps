package ex1_simple.c_java

object JavaPersonApp extends App {

  val person = new JavaPerson(10)

  person.setAge(10)
  person.setAge(20)
  person.setAge(10)

  println(person.getAge)
}
