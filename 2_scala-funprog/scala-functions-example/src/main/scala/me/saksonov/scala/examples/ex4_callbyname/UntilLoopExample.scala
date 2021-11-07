package me.saksonov.scala.examples.ex4_callbyname

import scala.annotation.tailrec

// Bash until Loop
// The until loop is used to execute a given set of commands as long as the given condition evaluates to false.
//
// The Bash until loop takes the following form:
//   until [CONDITION]
//   do
//   [COMMANDS]
//   done

object UntilLoopImpl {

  @tailrec
  def until(condition: => Boolean)(block: => Unit): Unit = {
    if (!condition) {
      block
      until(condition)(block)
    }
  }
}

object UntilLoopExampleApp extends App {

  import UntilLoopImpl._

  var x = 10

  until(x == 0) {
    x -= 1
    println(x)
  }

}
