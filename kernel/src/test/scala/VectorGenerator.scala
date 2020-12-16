/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 11:03 2020/12/16
 * @Modified By:
 */
object VectorGenerator {

  def genVector(dim: Int): Vector[Double] = {
    val arr = new Array[Double](dim)
    arr.map(item => scala.util.Random.nextDouble()*2 - 1).toVector
  }

  def genVecArray(dim: Int): Array[Double] = {
    val arr = new Array[Double](dim)
    arr.map(item => scala.util.Random.nextDouble()*2 - 1)
  }

}