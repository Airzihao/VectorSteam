package fun.airzihao.VectorDistanceTest

import fun.airzihao.VectorSteam.VectorDistance.ChebyshevDis
import fun.airzihao.VectorSteam.VectorDistance.ManhattanDis

import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 8:49 2020/12/16
 * @Modified By:
 */

class MinkowskiDisTest {
  val v0: Array[Double] = Array(0.0.toDouble, 0)
  val v1: Array[Double] = Array(1.0.toDouble, 1)
  val v2: Array[Double] = Array(3.0.toDouble, 4.0.toDouble)
  val v3: Array[Double] = Array(1.0.toDouble, 0, 0)
  val v4: Array[Double] = Array(0, 1.0.toDouble, 0)
  val v5: Array[Double] = Array(1.0.toDouble, 1, 0)
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