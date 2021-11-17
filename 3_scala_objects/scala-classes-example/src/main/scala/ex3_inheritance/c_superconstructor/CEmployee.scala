package ex3_inheritance.c_superconstructor

class CPerson(val age: Int, val name: String = "Vasya")

class CEmployee(age: Int, name: String, val salary: Double) extends CPerson(age, name)
