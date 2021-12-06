package examples.operations

object CollectionsOps extends App {

  val iterable = List(33, 34, 35)

  val filtered: List[Int] = iterable.filter(_ % 3 == 0)
  val maybeFound: Option[Int] = iterable.find(_.toString.startsWith("3"))

  //val _: Unit = iterable.foreach(println(_))

  val newIterable: List[Int] = iterable.map(_ + 1)

  println {
    iterable.flatMap(i => i.toChar.toString * i) mkString ("") // .map(f).flatten
  }

  // for-comprehensions

  val mapExample = for {
    a <- iterable
  } yield a + 1

  val flatMapExample: List[Int] = for {
    a <- iterable
    b <- iterable
  } yield a + b

  val flatMapExampleExplicit: List[Int] = iterable.flatMap(a => iterable.map(b => a + b))

  val filterExample = for {
    a <- iterable if a % 3 == 0
    b <- iterable if b % 5 == 0
  } yield s"$a $b"

  println(filterExample)

  println(iterable.map(_.toDouble).fold(1.0)(_ / _))
  println(iterable.reduce((a, b) => s"$a$b".toInt))

  val zipped: Seq[(String, Double)] = Seq("test1", "test2", "test3") zip Seq(1.0, 2.0, 3.0)

  println(zipped)

  // TODO: .unzip, .transpose

  val (strings, doubles) = zipped.unzip
}
