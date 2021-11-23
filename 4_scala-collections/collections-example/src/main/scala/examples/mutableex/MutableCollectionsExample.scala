package examples.mutableex

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object MutableCollectionsExample extends App {

  val queue = mutable.Queue.empty[Int] // FIFO
  val stack = mutable.Stack.empty[Int] // LIFO

  // like ArrayList - array with dynamic size
  val arrayBuffer = ArrayBuffer(1,2,3) += 2 // addOne

  // linked list inside instead of array
  val listBuffer = ListBuffer(1,2,3)

  // like Java, but built into Scala collections hierarchy
  val stringBuilder: mutable.IndexedSeq[Char] = new StringBuilder()
}
