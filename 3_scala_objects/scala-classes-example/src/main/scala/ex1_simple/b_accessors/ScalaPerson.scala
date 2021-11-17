package ex1_simple.b_accessors

//:javap -private SimpleScalaPerson

class SimpleScalaPerson(val age: Int) {
  logAge()

  def logAge(): Unit = println(age)
}

class ScalaPerson {
  private[this] var _age = 0

  def age: Int = _age
  def age_=(newAge: Int): Unit = {
    if (newAge > age) _age = newAge
  }

  def some(p: ScalaPerson): Unit = {
    ???
  }
}

object SimpleScalaPersonApp extends App {
  val person = new SimpleScalaPerson(0)
}

// Eiffel Lang: Uniform Access Principle

// The Uniform Access Principle (UAP) is a concept that states that
// "all services offered by a module should be available through a
// uniform notation, which does not betray whether they are implemented
// through storage or through computation"

class AScalaPerson(private val age: Int,
                  private var name: String) {
  def description = s"age: $age, name: $name"

  def this(name: String) {
    this(0, name)
  }

//  def age_=(newAge: Int): Unit = {
//    if (newAge > age) this.age = newAge
//  }

  def test: String = ""

  def test_=(a: Any) = println("test called")
}

object ScalaPersonApp extends App {
  val p = new AScalaPerson(10, "test")

  p.test = 0
}
