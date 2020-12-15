package VetcorDistanceTest

import VectorDistance.JaccardSim
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 17:51 2020/12/15
 * @Modified By:
 */
class JaccardSimTest {
  val v0 = Vector(0.0, 0)
  val v1 = Vector(1.0, 1)
  val v2 = Vector(3.0, 4.0)
  val v3 = Vector(1.0, 0, 0)
  val v4 = Vector(0, 1.0, 0)
  val v5 = Vector(1.0, 1, 0)

  @Test
  def test(): Unit = {
    Assert.assertEquals(0, JaccardSim.jaccardSim(v0, v1), 0.0001)
    Assert.assertEquals(0, JaccardSim.jaccardSim(v1, v0), 0.0001)
    Assert.assertEquals(1, JaccardSim.jaccardSim(v0, v0), 0.0001)
    Assert.assertEquals(1/3, JaccardSim.jaccardSim(v4, v5), 0.0001)
    Assert.assertEquals(1/3, JaccardSim.jaccardSim(v5, v4), 0.0001)
  }

}
