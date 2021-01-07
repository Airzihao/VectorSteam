package fun.airzihao.VectorSteam.search

import java.io.{BufferedInputStream, File, FileInputStream}
import fun.airzihao.VectorSteam.VectorDistance.EuclideanDis
import fun.airzihao.VectorSteam.commons.Utils.BasicUtils.timing
import fun.airzihao.VectorSteam.commons.{Asending, Desending, Utils, VecMolecule, VecMoleculeIter}
import fun.airzihao.VectorSteam.search.SearchEngineTest.{dims, moleSet, setSize, sortedMoleArray, srcFile, targetMolecule}
import org.junit.runners.MethodSorters
import org.junit.{Assert, FixMethodOrder, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:20 2021/1/5
 * @Modified By:
 */
object SearchEngineTest {
  val dims = 128
  val setSize: Int = 100000
  val targetMolecule: VecMolecule = VecMolecule(setSize, new Array[Float](dims).map(_ => 0.3f))
  val sortedMoleArray: Array[VecMolecule] = new Array[Float](setSize).zipWithIndex.map(item => VecMolecule(item._2, new Array[Float](dims).map(_ =>item._2.toFloat)))
  val moleSet: Set[VecMolecule] = sortedMoleArray.reverse.toSet
  val srcFile: File = new File("../tools/src/test/resources/sift1m-mole")
}

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SearchEngineTest {

  @Test
  def test1Asending(): Unit = {
    val expectedMolecule1 = sortedMoleArray.head
    val actualMolecule1 = timing("get 1st, asend ", SearchEngine.getNearestMolecule(targetMolecule, moleSet, EuclideanDis.eucDis(_, _), Asending))
    molesEqual(expectedMolecule1, actualMolecule1)
  }

  @Test
  def test1Desending(): Unit = {
    val expectedMolecule = sortedMoleArray.reverse.head
    val actualMolecule = timing("get 1st, desend ", SearchEngine.getNearestMolecule(targetMolecule, moleSet, EuclideanDis.eucDis(_, _), Desending))
    molesEqual(expectedMolecule, actualMolecule)
  }

  @Test
  def test2Asending(): Unit = {
    val result = timing("get 5 nearest.", SearchEngine.getKNearestMolecules(targetMolecule, moleSet, EuclideanDis.eucDis(_, _), Asending, 5))
    Assert.assertEquals(5, result.length)
    result.zipWithIndex.map(result => molesEqual(result._1, sortedMoleArray(result._2)))
  }

  @Test
  def test2Desending(): Unit = {
    val result = timing("get 5 nearest.", SearchEngine.getKNearestMolecules(targetMolecule, moleSet, EuclideanDis.eucDis(_, _), Desending, 5))
    Assert.assertEquals(5, result.length)
    result.zipWithIndex.map(result => molesEqual(result._1, sortedMoleArray.reverse(result._2)))
  }

  @Test
  def test3Asending(): Unit = {
    val result = timing("get all sorted. ", SearchEngine.getKNearestMolecules(targetMolecule, moleSet, EuclideanDis.eucDis(_, _), Asending, 2*setSize))
    Assert.assertEquals(setSize, result.length)
    result.zip(sortedMoleArray).foreach(pair => molesEqual(pair._1, pair._2))
  }

  @Test
  def test3Desending(): Unit = {
    val result = timing("get all sorted. ", SearchEngine.getKNearestMolecules(targetMolecule, moleSet, EuclideanDis.eucDis(_, _), Desending, 2*setSize))
    Assert.assertEquals(setSize, result.length)
    result.zip(sortedMoleArray.reverse).foreach(pair => molesEqual(pair._1, pair._2))
  }

  @Test
  def test4(): Unit = {
    val iter = new VecMoleculeIter(new BufferedInputStream(new FileInputStream(srcFile), 10 * 1024 * 1024), Utils.BasicUtils.getVecMoleculeSize(dims))
    SearchEngine.getNearestMolecule(targetMolecule, iter, EuclideanDis.eucDis(_, _), Asending)
  }

  @Test
  def test5(): Unit = {
    val iter = new VecMoleculeIter(new BufferedInputStream(new FileInputStream(srcFile), 10 * 1024 * 1024), Utils.BasicUtils.getVecMoleculeSize(dims))
    SearchEngine.getKNearestMolecules(targetMolecule, iter, EuclideanDis.eucDis(_, _), Asending, 5)
  }

  def molesEqual(mole1: VecMolecule, mole2: VecMolecule): Unit = {
    Assert.assertEquals(mole1.id, mole2.id)
    Assert.assertArrayEquals(mole1.vec, mole2.vec, 0.0001f)
  }

}
