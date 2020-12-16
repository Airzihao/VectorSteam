package fun.airzihao.VectorDistanceTest

import fun.airzihao.VectorDistance.CosineSim
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 17:01 2020/12/15
 * @Modified By:
 */
class CosineSimTest {
  val v0 = Vector(0.0, 0)
  val v1 = Vector(1.0, 1)
  val v2 = Vector(3.0, 4.0)
  val v3 = Vector(1.0, 0, 0)
  val v4 = Vector(0, 1.0, 0)

  @Test
  def test1(): Unit ={
    Assert.assertEquals(0, CosineSim.cosineSim(v0, v1), 0.001)
    Assert.assertEquals(0, CosineSim.cosineSim(v1, v0), 0.001)
    Assert.assertEquals(7/(5*math.sqrt(2)), CosineSim.cosineSim(v1, v2), 0.001)
    Assert.assertEquals(7/(5*math.sqrt(2)), CosineSim.cosineSim(v2, v1), 0.001)
    Assert.assertEquals(0, CosineSim.cosineSim(v3, v4), 0.001)
    Assert.assertEquals(0, CosineSim.cosineSim(v4, v3), 0.001)
  }

}
