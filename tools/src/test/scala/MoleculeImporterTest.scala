import java.io.File

import fun.airzihao.VectorSteam.IOUtils.{MoleculeImporter, SteamExporter}
import org.junit.runners.MethodSorters
import org.junit.{Assert, FixMethodOrder, Test}
import MoleculeImporterTest.{file, importer, stepLength, vecCountNum, vecMolecules}
import fun.airzihao.VectorSteam.commons.{MetaDataManager, Utils, VecMolecule, VectorGenerator}

import scala.util.Random
/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:14 2021/1/4
 * @Modified By:
 */

object MoleculeImporterTest {
  val dims: Int = 128
  val vecCountNum = 1000
  val stepLength: Int = Utils.getVecMoleculeSize(dims)
  val file = {
    val f = new File("./src/test/resources/testFile")
    if(f.exists()) f.delete()
    f
  }
  val importer = new MoleculeImporter(file, dims)

  val generator = VectorGenerator
  val vecMolecules: Array[VecMolecule] = new Array[Int](vecCountNum).map(item => VecMolecule(MetaDataManager.availableMoleculeId, generator.genVecArray(dims)))
}

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MoleculeImporterTest {
  @Test
  def test1(): Unit = {
    val exporter = new SteamExporter(file)
    Assert.assertEquals(vecCountNum, vecMolecules.length)
    val iter = vecMolecules.iterator
    exporter.writeVecMolecule(iter.next())
    Assert.assertEquals(stepLength, file.length())
    exporter.writeVecMolecule(iter.next())
    Assert.assertEquals(file.length(), stepLength*2)
    exporter.writeBatch(iter)
    Assert.assertEquals(file.length(), stepLength*vecCountNum)
  }

  @Test
  def test2(): Unit = {
    val randomIndex = Random.nextInt(vecCountNum)
    val vecMolecule = importer.getVecMolecule(randomIndex)
    Assert.assertEquals(vecMolecules(randomIndex).id, vecMolecule.id)
    Assert.assertArrayEquals(vecMolecules(randomIndex).vec, vecMolecule.vec, 0.0001f)
  }

  @Test
  def test3(): Unit = {
    val iter1 = importer.getVecMolecules
    val iter2 = vecMolecules.iterator
    while (iter1.hasNext) {
      val vec1 = iter1.next()
      val vec2 = iter2.next()
      Assert.assertEquals(vec2.id, vec1.id)
      Assert.assertArrayEquals(vec2.vec, vec1.vec, 0.001f)
    }
  }

}