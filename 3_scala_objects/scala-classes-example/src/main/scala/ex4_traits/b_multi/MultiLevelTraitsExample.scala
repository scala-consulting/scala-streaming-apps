package ex4_traits.b_multi

import ex4_traits.a_traits.{ConsoleLogger, Logger, LoggingMyService}

trait TimestampLogger extends Logger {
  abstract override def log(msg: String): Unit = super.log(s"${java.time.Instant.now()} $msg")
}

trait ShortLogger extends Logger {
  abstract override def log(msg: String): Unit = super.log(
    if (msg.length > 15) s"${msg.substring(0,12)}..." else msg
  )
}

object MyServiceApp2 extends App {

  // do not forget about linearization order in instance!

//  val svc1 = new LoggingMyService with ConsoleLogger with TimestampLogger with ShortLogger
  val svc2 = new LoggingMyService with ConsoleLogger with ShortLogger with TimestampLogger

//  svc1.doWork()
  svc2.doWork()
}