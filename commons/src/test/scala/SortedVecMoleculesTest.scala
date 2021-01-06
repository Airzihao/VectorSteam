import TestBase.molesEqual
import fun.airzihao.VectorSteam.commons.{Asending, Desending, SortedVecMolecules, VecMolecule}
import org.junit.runners.MethodSorters
import org.junit.{Assert, FixMethodOrder, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 19:22 2021/1/5
 * @Modified By:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SortedVecMoleculesTest {
  val maxSize = 3
  val dataSize = 100
  val dims = 128
  val targetMole: VecMolecule = VecMolecule(0, new Array[Int](dims).map(_ => 0.3f))
  val testData: Array[(Float, VecMolecule)] = new Array[Float](dataSize).zipWithIndex
    .map(item => (item._2.toFloat, VecMolecule(item._2+1, new Array[Float](dims).map(_ => item._2.toFloat))))
  val testDataSet: Set[(Float, VecMolecule)] = testData.toSet

  val asendResult: Array[(Float, VecMolecule)] = testData.slice(0, 3)
  val desendResult: Array[(Float, VecMolecule)] = testData.slice(97, 100).reverse

  @Test
  def test1(): Unit = {
    val sorter: SortedVecMolecules = new SortedVecMolecules(maxSize, Asending)
    testDataSet.iterator.foreach(disPair => {
      sorter.tryToInsert(disPair)
    })
    val result = sorter.sortedMoleculesWithDistance
    result.zip(asendResult).foreach(pair => {
      Assert.assertEquals(pair._2._1, pair._1._1, 0.001f)
      molesEqual(pair._2._2, pair._1._2)
    })
  }

  @Test
  def test2(): Unit = {
    val sorter: SortedVecMolecules = new SortedVecMolecules(maxSize, Desending)
    testDataSet.iterator.foreach(disPair => {
      sorter.tryToInsert(disPair)
    })
    val result = sorter.sortedMoleculesWithDistance
    result.zip(desendResult).foreach(pair => {
      Assert.assertEquals(pair._2._1, pair._1._1, 0.001f)
      molesEqual(pair._2._2, pair._1._2)
    })
  }


}
