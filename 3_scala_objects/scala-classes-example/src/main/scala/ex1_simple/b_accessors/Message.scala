package ex1_simple.b_accessors

class Message {

  val timestamp = new java.util.Date
}

object MessageApp extends App {

  val message = new Message

  println(message.timestamp)
}
