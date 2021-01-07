import SearchEngineTest.{dims, importer, testData}
import fun.airzihao.VectorSteam.IOTools.MoleculeImporter
import fun.airzihao.VectorSteam.VectorDistance.CosineSim
import fun.airzihao.VectorSteam.clustering.{DistanceCluster, RandomForestSpliter}
import fun.airzihao.VectorSteam.commons.Utils.BasicUtils.timing
import fun.airzihao.VectorSteam.commons.{Desending, PureVec, VecMolecule}
import fun.airzihao.VectorSteam.search.SearchEngine
import org.junit.{Assert, Test}

import java.io.File
import scala.util.Random

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:34 2021/1/6
 * @Modified By:
 */
object SearchEngineTest {
  val dims = 128
  val srcFile = new File("../tools/src/test/resources/sift1m-mole")
  val importer = new MoleculeImporter(srcFile, dims)
  val testData: Array[VecMolecule] = new Array[Int](10).map(_ => importer.getVecMolecule(Random.nextInt(10000)))

}
class SearchEngineTest {
  @Test
  def test1(): Unit = {

    val searchPath = "../tools/src/test/resources/partition-32"
    val splitVecs = new MoleculeImporter(new File(s"$searchPath/splitVecs-5"), dims).getVecMolecules.map(item => PureVec(item.vec)).toArray
    val spliter = new RandomForestSpliter(splitVecs)

    testData.foreach(mole => {
      val rltByPartSearch = timing(SearchEngine.getNearestMolecule(mole, spliter, searchPath, CosineSim.cosineSim(_, _), Desending))
      val rltByNaiveSearch = timing(SearchEngine.getNearestMolecule(mole, importer.getVecMolecules, CosineSim.cosineSim(_, _), Desending))
      molesEqual(mole, rltByPartSearch)
      molesEqual(mole, rltByNaiveSearch)
    })
  }

  @Test
  def test2(): Unit = {
    val searchPath = "../tools/src/test/resources/part-disCluster"
    val splitVecs = new MoleculeImporter(new File(s"$searchPath/splitVecs-100"), dims).getVecMolecules.toArray
    val spliter = new DistanceCluster(splitVecs)
      testData.foreach(mole => {
      val rltByPartSearch = timing(SearchEngine.getNearestMolecule(mole, spliter, searchPath, CosineSim.cosineSim(_, _), Desending))
      val rltByNaiveSearch = timing(SearchEngine.getNearestMolecule(mole, importer.getVecMolecules, CosineSim.cosineSim(_, _), Desending))
      molesEqual(mole, rltByPartSearch)
      molesEqual(mole, rltByNaiveSearch)
    })
  }

  def molesEqual(mole1: VecMolecule, mole2: VecMolecule): Unit = {
    Assert.assertEquals(mole1.id, mole2.id)
    Assert.assertArrayEquals(mole1.vec, mole2.vec, 0.001f)
  }



}
