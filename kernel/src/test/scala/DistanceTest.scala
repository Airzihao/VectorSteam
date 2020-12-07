import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:49 2020/12/7
 * @Modified By:
 */
class DistanceTest extends TestBase {
  val vector1 = Vector(3.0,4)
  val vector2 = Vector(1.0, 1, 1)

  @Test
  def testEucLength() = {
    Assert.assertEquals(5, EuclideanDis.eucLength(vector1), 0.001)
    Assert.assertEquals(math.sqrt(3), EuclideanDis.eucLength(vector2), 0.001)
  }

  def perfEucLength(count: Int, dim: Int): Unit = {
    val tempArr = new Array[Int](count)
    val vecArr: Array[Vector[Double]] = tempArr.map(item => VectorGenerator.genVector(dim))
    println("generated")
    timing(vecArr.foreach(vec => EuclideanDis.eucLength(vec)))
  }

  @Test
  def test1(): Unit ={
    perfEucLength(20000, 1280)
  }

}
