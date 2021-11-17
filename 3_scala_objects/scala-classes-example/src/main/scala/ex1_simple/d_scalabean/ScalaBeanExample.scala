package ex1_simple.d_scalabean

import scala.beans.BeanProperty

class ScalaBean {

  @BeanProperty var value: Int = 0

}

object ScalaBeanApp extends App {
  val scalaBean = new ScalaBean

  scalaBean.setValue(0)

  scalaBean.getValue
}
