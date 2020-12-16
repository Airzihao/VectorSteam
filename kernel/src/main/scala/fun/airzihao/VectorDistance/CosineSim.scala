package fun.airzihao.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:43 2020/12/15
 * @Modified By:
 */
object CosineSim {
  // cosSim = (a*b)/(|a|*|b|)
  def cosineSim(vector1: Vector[Double], vector2: Vector[Double]): Double = {
    val numerator: Double = vector1.zip(vector2).map(pair => pair._1*pair._2).sum
    val denominator: Double = EuclideanDis.eucLength(vector1) * EuclideanDis.eucLength(vector2)
    if (denominator == 0) 0
    else numerator / denominator
  }
}
