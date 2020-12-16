package fun.airzihao.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:44 2020/12/15
 * @Modified By:
 */
object JaccardSim {
  //  Jaccard similarity coefficient
  def jaccardSim(vector1: Vector[Double], vector2: Vector[Double], delta: Double = 0.0001): Double = {
    val length = vector1.length
    val replicItemCount = vector1.zip(vector2).map(pair => {
      if(math.abs(pair._1 - pair._2)< delta) 1
      else 0
    }).sum
    replicItemCount/length
  }
}
