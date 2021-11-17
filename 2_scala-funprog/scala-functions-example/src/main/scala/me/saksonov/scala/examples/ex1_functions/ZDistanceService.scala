package me.saksonov.scala.examples.ex1_functions

class Point(private val x: Double, private val y: Double) {
  def substituteIn(f: (Double, Double) => Double): Double = f(x,y)

  override def toString: String = s"($x, $y)"
}

class ZDistanceService {
  private val distanceF = (x: Double, y: Double) => math.sqrt(x * x + y * y)

  //def z(p: Point): Double = distanceF(p.x, p.y)

  def distanceTo(p: Point): Double = p.substituteIn(distanceF)
}

object ZDistanceServiceExampleApp extends App {
  val distanceService = new ZDistanceService

  val point = new Point(3, 4)

  print(s"Distance to point $point = ${distanceService.distanceTo(point)}")
}