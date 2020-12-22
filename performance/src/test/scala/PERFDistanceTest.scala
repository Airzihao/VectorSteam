import fun.airzihao.VectorSteam.Utils.Utils.timing
import fun.airzihao.VectorSteam.Utils.VectorGenerator
import fun.airzihao.VectorSteam.VectorDistance.EuclideanDis
import org.junit.Test

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:28 2020/12/15
 * @Modified By:
 */
class PERFDistanceTest {

  @Test
  def test() {
    perfEucLength(1000000, 256)
  }

  //array performs better, nearly 50% time cost.
  def perfEucLength(count: Int, dim: Int): Unit = {
    val vec = VectorGenerator.genVector(dim)
    val arr = VectorGenerator.genVecArray(dim)
    println("generated")
    timing(for(i<-1 to count) EuclideanDis.eucLength(arr))
  }

  def perfEucDis(count: Int, dim: Int): Unit = {
    val a1 = VectorGenerator.genVecArray(128)
    val a2 = VectorGenerator.genVecArray(128)
    for(i <- 1 to count) EuclideanDis.eucDis(a1, a2)
  }

}
