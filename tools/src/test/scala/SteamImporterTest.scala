import java.io.File

import fun.airzihao.VectorSteam.Utils.{SteamExporter, SteamImporter, VectorGenerator}
import fun.airzihao.VectorSteam.kernel.{MetaDataManager, VecMolecule}
import org.junit.runners.MethodSorters
import org.junit.{Assert, FixMethodOrder, Test}
import SteamImporterTest.{vecArray, file, vecCountNum, stepLength, importer}

import scala.util.Random
/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:14 2021/1/4
 * @Modified By:
 */

object SteamImporterTest {
  val dims: Int = 128
  val vecCountNum = 1000
  val stepLength: Int = 8 + 4 + 4*dims
  val file = {
    val f = new File("./src/test/resources/testFile")
    if(f.exists()) f.delete()
    f
  }
  val importer = new SteamImporter(file, dims)

  val generator = VectorGenerator
  val vecArray: Array[VecMolecule] = new Array[Int](vecCountNum).map(item => VecMolecule(MetaDataManager.availableMoleculeId, generator.genVecArray(dims)))
}

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SteamImporterTest {
  @Test
  def test1(): Unit = {
    val exporter = new SteamExporter(file)
    Assert.assertEquals(vecCountNum, vecArray.length)
    val iter = vecArray.iterator
    exporter.writeVecMolecule(iter.next())
    Assert.assertEquals(stepLength, file.length())
    exporter.writeVecMolecule(iter.next())
    Assert.assertEquals(file.length(), stepLength*2)
    exporter.writeBatch(iter)
    Assert.assertEquals(file.length(), stepLength*vecCountNum)
    exporter.close()
  }

  @Test
  def test2(): Unit = {
    val randomIndex = Random.nextInt(vecCountNum)
    val vecMolecule = importer.getVecMolecule(randomIndex)
    Assert.assertEquals(vecArray(randomIndex).id, vecMolecule.id)
    Assert.assertArrayEquals(vecArray(randomIndex).vec, vecMolecule.vec, 0.0001f)
  }

  @Test
  def test3(): Unit = {
    val iter1 = importer.getVecMolecules
    val iter2 = vecArray.iterator
    while (iter1.hasNext) {
      val vec1 = iter1.next()
      val vec2 = iter2.next()
      Assert.assertEquals(vec2.id, vec1.id)
      Assert.assertArrayEquals(vec2.vec, vec1.vec, 0.001f)
    }
  }

}
