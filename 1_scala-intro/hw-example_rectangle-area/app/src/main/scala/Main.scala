import scala.io.StdIn

object Main extends App {

  val rectangleService = new RectangleService

  print("Rectangle Height: ")
  val height = StdIn.readDouble()

  print("Rectangle Width: ")
  val width = StdIn.readDouble()

  val rd = RectangleData(height, width)

  println(s"Rectangle Area: ${rectangleService.area(rd)}")

  println("Press RETURN...")
  StdIn.readLine()
}
