package fun.airzihao.VectorSteam.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:41 2020/12/15
 * @Modified By:
 */
object EuclideanDis {
  def eucDis(vector1: Seq[Float], vector2: Seq[Float]): Float = {
    val squareSum: Float = vector1.zip(vector2).map( pair => math.pow((pair._1 - pair._2), 2)).sum.toFloat
    math.sqrt(squareSum).toFloat
  }

  def stdEucDis(vector1: Seq[Float], vector2: Seq[Float]): Float = {
    val stdVector1 = stdVector(vector1)
    val stdVector2 = stdVector(vector2)
    eucDis(stdVector1, stdVector2)
  }

  def stdVector(vector: Seq[Float]): Seq[Float] = {
    val length = eucLength(vector)
    if (length == 0) vector
    else vector.map(item => item/length)
  }

  def eucLength(vector: Seq[Float]): Float = {
    val squareSum: Float = vector.map(elem => elem*elem).sum
    math.sqrt(squareSum).toFloat
  }
}
