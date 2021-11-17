package ex4_traits.a_traits

trait Logger {
  def log(msg: String): Unit
}

trait ConsoleLogger extends Logger {
  def log(msg: String): Unit = println(msg)
}

object TraitsExampleApp extends App with ConsoleLogger {
  log("Test")
}

trait MyService {
  def doWork(): Unit
}

abstract class LoggingMyService extends MyService with Logger {

  override def doWork(): Unit = {
    log("Hello! I am doing some work, stay tuned!")
  }
}

object MyServiceApp extends App {
  val svc = new LoggingMyService with ConsoleLogger

  svc.doWork()
}