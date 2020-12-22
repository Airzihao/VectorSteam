package fun.airzihao.VectorSteam.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:41 2020/12/15
 * @Modified By:
 */
object EuclideanDis {
  def eucDis(vector1: Array[Double], vector2: Array[Double]): Double = {
    val squareSum = vector1.zip(vector2).map( pair => math.pow((pair._1 - pair._2), 2)).sum
    math.sqrt(squareSum)
  }

  def stdEucDis(vector1: Array[Double], vector2: Array[Double]): Double = {
    val stdVector1 = stdVector(vector1)
    val stdVector2 = stdVector(vector2)
    eucDis(stdVector1, stdVector2)
  }

  def stdVector(vector: Array[Double]): Array[Double] = {
    val length = eucLength(vector)
    if (length == 0) vector
    else vector.map(item => (item/length).toDouble)
  }

  def eucLength(vector: Array[Double]): Double = {
    val squareSum: Double = vector.map(elem => elem*elem).sum
    math.sqrt(squareSum)
  }
}
