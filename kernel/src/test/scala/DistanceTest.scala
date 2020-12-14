import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:49 2020/12/7
 * @Modified By:
 */
class DistanceTest extends TestBase {
  // perfEucDis: performanceEuclideanDistance

  @Test
  def testEucLength() = {
    val vector1 = Vector(3.0,4)
    val vector2 = Vector(1.0, 1, 1)
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
  def testEucDis() = {
    val vector1 = Vector(3.0, 4)
    val vector2 = Vector(0.0, 0)
    val dis = EuclideanDis.eucDis(vector1, vector2)
    Assert.assertEquals(5, dis, 0.0001)
  }

  def perfEucDis(count: Int, dim: Int): Unit = {
    val v1 = VectorGenerator.genVector(dim)
    val v2 = VectorGenerator.genVector(dim)
    for(i <- 1 to count) EuclideanDis.eucDis(v1, v2)
  }
  @Test
  def test2(): Unit = {
    timing(perfEucDis(1000000, 128))
  }

  @Test
  def test1(): Unit ={
    perfEucLength(200000, 128)
  }

}
