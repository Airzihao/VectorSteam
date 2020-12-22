package fun.airzihao.VectorDistanceTest

import fun.airzihao.VectorSteam.VectorDistance.JaccardSim
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 17:51 2020/12/15
 * @Modified By:
 */
class JaccardSimTest {
  val v0: Array[Double] = Array(0.0.toDouble, 0)
  val v1: Array[Double] = Array(1.0.toDouble, 1)
  val v2: Array[Double] = Array(3.0.toDouble, 4.0.toDouble)
  val v3: Array[Double] = Array(1.0.toDouble, 0, 0)
  val v4: Array[Double] = Array(0, 1.0.toDouble, 0)
  val v5: Array[Double] = Array(1.0.toDouble, 1, 0)

  @Test
  def test(): Unit = {
    Assert.assertEquals(0, JaccardSim.jaccardSim(v0, v1), 0.0001)
    Assert.assertEquals(0, JaccardSim.jaccardSim(v1, v0), 0.0001)
    Assert.assertEquals(1, JaccardSim.jaccardSim(v0, v0), 0.0001)
    Assert.assertEquals(1/3, JaccardSim.jaccardSim(v4, v5), 0.0001)
    Assert.assertEquals(1/3, JaccardSim.jaccardSim(v5, v4), 0.0001)
  }

}
