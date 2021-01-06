package fun.airzihao.VectorSteam.clustering

import fun.airzihao.VectorSteam.commons.VecMolecule
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 10:50 2021/1/5
 * @Modified By:
 */
class ClusterUtilsTest {
  val vec1 = VecMolecule(1, new Array[Float](128).map(_ => 0.5f))
  val vec2 = VecMolecule(2, new Array[Float](128).map(_ => -1f))
  val vec3 = VecMolecule(3, new Array[Float](128).map(_ => 1f))
  val vecMolecules: Iterator[VecMolecule] = Array(vec1, vec2, vec3).iterator
  val expectedBound: Array[(Float, Float)] = new Array[Float](128).map(_ => (-1f, 1f))

  @Test
  def test1(): Unit = {
    val actualBound = ClusterUtils.getSpaceBound(vecMolecules)
    expectedBound.zip(actualBound).foreach(pair => {
      Assert.assertEquals(pair._1, pair._2)
    })
  }

}
