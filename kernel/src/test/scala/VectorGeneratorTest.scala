import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:21 2020/12/7
 * @Modified By:
 */
class VectorGeneratorTest extends TestBase {
  val generator = VectorGenerator

  @Test
  def testVector() = {
    val v_8 = generator.genVector(8)
    Assert.assertEquals(8, v_8.length)
    Assert.assertEquals(true, v_8.isInstanceOf[Vector[Double]])
    println(v_8)
  }

  @Test
  def performanceTest() = {
    val times = math.pow(10, 4).toInt
    print("genVector:")
    timing(for (i<-1 to times) generator.genVector(256))
    print("genArr:")
    timing(for (i<-1 to times) generator.genArray(256))
  }

  @Test
  def multiRound(): Unit = {
    val roundCount = 5
    for (i <- 1 to roundCount) {
      performanceTest()
    }
  }



}
