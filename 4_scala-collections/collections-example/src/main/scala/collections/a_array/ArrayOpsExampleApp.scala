package collections.a_array

import scala.collection.ArrayOps

object ArrayOpsExampleApp extends App {

  // Predef.intArrayOps
  val arr: ArrayOps[Int] = Array(1, 2, 3) // AnyVal

  println(arr map (_ + 1) mkString (", "))

}
