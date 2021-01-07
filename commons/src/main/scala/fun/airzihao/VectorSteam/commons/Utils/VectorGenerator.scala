package fun.airzihao.VectorSteam.commons.Utils

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 11:03 2020/12/16
 * @Modified By:
 */
object VectorGenerator {

  def genVector(dim: Int): Vector[Float] = {
    val arr = new Array[Float](dim)
    arr.map(item => scala.util.Random.nextFloat() * 2 - 1).toVector
  }

  def genVecArray(dim: Int): Array[Float] = {
    val arr = new Array[Float](dim)
    arr.map(item => scala.util.Random.nextFloat() * 2 - 1)
  }

}
