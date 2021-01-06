package fun.airzihao.VectorSteam.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:43 2020/12/15
 * @Modified By:
 */
object CosineSim {
  // cosSim = (a*b)/(|a|*|b|)
  def cosineSim(vector1: Seq[Float], vector2: Seq[Float]): Float = {
    val numerator: Float = vector1.zip(vector2).map(pair => pair._1*pair._2).sum
    val denominator: Float = EuclideanDis.eucLength(vector1) * EuclideanDis.eucLength(vector2)
    if (denominator == 0) 0
    else numerator / denominator
  }
}
