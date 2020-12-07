/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 10:26 2020/12/7
 * @Modified By:
 */

object VectorGenerator {

  def genVector(dim: Int): Vector[Double] = {
    val arr = new Array[Double](dim)
    arr.map(item => scala.util.Random.nextDouble()*2 - 1).toVector
  }

  def genArray(dim: Int): Array[Double] = {
    val arr = new Array[Double](dim)
    arr.map(item => scala.util.Random.nextDouble()*2 - 1)
  }

}