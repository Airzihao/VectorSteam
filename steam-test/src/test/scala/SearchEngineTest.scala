import java.io.File

import SearchEngineTest.{importer, searchPath, spliter, testData}
import fun.airzihao.VectorSteam.IOUtils.MoleculeImporter
import fun.airzihao.VectorSteam.VectorDistance.CosineSim
import fun.airzihao.VectorSteam.clustering.RandomForestSpliter
import fun.airzihao.VectorSteam.commons.Utils.timing
import fun.airzihao.VectorSteam.commons.{Desending, PureVec, VecMolecule}
import fun.airzihao.VectorSteam.search.SearchEngine
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:34 2021/1/6
 * @Modified By:
 */
object SearchEngineTest {
  val dims = 128
  val srcFile = new File("../tools/src/test/resources/sift1m-mole")
  val searchPath = "../tools/src/test/resources/partition-32"
  val importer = new MoleculeImporter(srcFile, dims)
  val testData: Array[VecMolecule] = new Array[Int](10).map(_ => importer.getVecMolecules.next())

  val splitVecs = new MoleculeImporter(new File(s"$searchPath/splitVecs-5"), dims).getVecMolecules.map(item => PureVec(item.vec)).toArray
  val spliter = new RandomForestSpliter(splitVecs)

}
class SearchEngineTest {
  @Test
  def test1(): Unit = {
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
