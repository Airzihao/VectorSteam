package fun.airzihao.VectorSteam.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 15:16 2021/1/8
 * @Modified By:
 */
object VecSimMeasure {

  def cosineSim(vec1: Seq[Float], vec2: Seq[Float]) = CosineSim.cosineSim(vec1, vec2)
  def euclideanDis()(vec1: Seq[Float], vec2: Seq[Float]) = EuclideanDis.eucDis(vec1, vec2)

}
