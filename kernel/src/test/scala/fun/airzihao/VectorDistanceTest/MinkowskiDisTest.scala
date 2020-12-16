package fun.airzihao.VectorDistanceTest

import fun.airzihao.VectorDistance.ChebyshevDis
import fun.airzihao.VectorDistance.ManhattanDis

import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 8:49 2020/12/16
 * @Modified By:
 */

class MinkowskiDisTest {
  val v0 = Vector(0.0, 0)
  val v1 = Vector(1.0, 1)
  val v2 = Vector(3.0, 4.0)
  val v3 = Vector(1.0, 0, 0)
  val v4 = Vector(0, 1.0, 0)
  val v5 = Vector(1.0, 1, 0)
}

class ChebyshevDisTest extends MinkowskiDisTest {

  @Test
  def test(): Unit ={
    Assert.assertEquals(1, ChebyshevDis.chebyDis(v0, v1), 0.001)
    Assert.assertEquals(1, ChebyshevDis.chebyDis(v1, v0), 0.001)
    Assert.assertEquals(0, ChebyshevDis.chebyDis(v1, v1), 0.001)
    Assert.assertEquals(4, ChebyshevDis.chebyDis(v0, v2), 0.001)
    Assert.assertEquals(4, ChebyshevDis.chebyDis(v2, v0), 0.001)
  }
}

class ManhattanDis extends MinkowskiDisTest {

  @Test
  def test(): Unit ={
//    val man = ManhattanDis
    Assert.assertEquals(0, ManhattanDis.manhattanDis(v1, v1), 0.001)
    Assert.assertEquals(7, ManhattanDis.manhattanDis(v0, v2), 0.001)
    Assert.assertEquals(7, ManhattanDis.manhattanDis(v2, v0), 0.001)
    Assert.assertEquals(2, ManhattanDis.manhattanDis(v3, v4), 0.001)
    Assert.assertEquals(2, ManhattanDis.manhattanDis(v4, v3), 0.001)
  }

}