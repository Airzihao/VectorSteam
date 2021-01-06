package fun.airzihao.VectorSteam.clustering

import fun.airzihao.VectorSteam.commons.{PureVec, VecMolecule}
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:23 2021/1/6
 * @Modified By:
 */

class RandomForestSpliterTest {

  val splitVec1 = PureVec(Array(1f, 1f))
  val splitVec2 = PureVec(Array(-1f, -1f))

  val mole1 = VecMolecule(0, Array(1, 0))
  val result1 = Array(true, false)

  val mole2 = VecMolecule(1, Array(0, 1))
  val result2 = Array(true, false)

  val mole3 = VecMolecule(2, Array(-1, 0))
  val result3 = Array(false, true)

  val mole4 = VecMolecule(3, Array(0, -1))
  val result4 = Array(false, true)

  val pairArr = Array((mole1, result1), (mole2, result2), (mole3, result3), (mole4, result4))
  val spliter = new RandomForestSpliter(Array(splitVec1, splitVec2))
  @Test
  def test1(): Unit = {
    pairArr.foreach(pair => {
      Assert.assertArrayEquals(pair._2, spliter.getHashValue(pair._1))
    })
  }

}
