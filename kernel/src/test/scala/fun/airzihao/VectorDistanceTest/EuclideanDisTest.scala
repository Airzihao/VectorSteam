package fun.airzihao.VectorDistanceTest

import fun.airzihao.VectorSteam.VectorDistance.EuclideanDis
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 20:45 2020/12/15
 * @Modified By:
 */
class EuclideanDisTest {
  val v0: Array[Double] = Array(0.0.toDouble, 0)
  val v1: Array[Double] = Array(1.0.toDouble, 1)
  val v2: Array[Double] = Array(3.0.toDouble, 4.0.toDouble)
  val v3: Array[Double] = Array(1.0.toDouble, 0, 0)
  val v4: Array[Double] = Array(0, 1.0.toDouble, 0)

  @Test
  def testEucDis(): Unit = {
    Assert.assertEquals(math.sqrt(2), EuclideanDis.eucDis(v0, v1), 0.001)
    Assert.assertEquals(math.sqrt(2), EuclideanDis.eucDis(v1, v0), 0.001)
    Assert.assertEquals(0, EuclideanDis.eucDis(v1, v1), 0.001)
    Assert.assertEquals(5, EuclideanDis.eucDis(v0, v2), 0.001)
    Assert.assertEquals(5, EuclideanDis.eucDis(v2, v0), 0.001)
    Assert.assertEquals(math.sqrt(2), EuclideanDis.eucDis(v3, v4), 0.001)
    Assert.assertEquals(math.sqrt(2), EuclideanDis.eucDis(v4, v3), 0.001)
  }

  @Test
  def testEucLength(): Unit = {
    Assert.assertEquals(0, EuclideanDis.eucLength(v0), 0.001)
    Assert.assertEquals(math.sqrt(2), EuclideanDis.eucLength(v1), 0.001)
    Assert.assertEquals(5, EuclideanDis.eucLength(v2), 0.001)
    Assert.assertEquals(1, EuclideanDis.eucLength(v3), 0.001)
  }

  @Test
  def testStdEucDis(): Unit = {
    Assert.assertEquals(1, EuclideanDis.stdEucDis(v0, v1), 0.001)
    Assert.assertEquals(1, EuclideanDis.stdEucDis(v1, v0), 0.001)
    Assert.assertEquals(0, EuclideanDis.stdEucDis(v1, v1), 0.001)
    Assert.assertEquals(1, EuclideanDis.stdEucDis(v0, v2), 0.001)
    Assert.assertEquals(1, EuclideanDis.stdEucDis(v2, v0), 0.001)
    Assert.assertEquals(math.sqrt(2), EuclideanDis.stdEucDis(v3, v4), 0.001)
    Assert.assertEquals(math.sqrt(2), EuclideanDis.stdEucDis(v4, v3), 0.001)
  }
}
