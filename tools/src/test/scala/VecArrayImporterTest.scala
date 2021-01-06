import java.io.File

import fun.airzihao.VectorSteam.IOUtils.{SteamExporter, VecArrayImporter, VecExporter}
import fun.airzihao.VectorSteam.commons.{MetaDataManager, Utils, VecMolecule, VectorGenerator}
import org.junit.{Assert, FixMethodOrder, Test}
import org.junit.runners.MethodSorters
import VecArrayImporterTest.{dims, file, generator, importer, stepLength, vecArray, vecCountNum}

import scala.util.Random

/**
  * @Author: Airzihao
  * @Description:
  * @Date: Created in 22:03 2021/1/4
  * @Modified By:
  */
object VecArrayImporterTest {
  val dims: Int = 128
  val vecCountNum = 1000
  val stepLength: Int = Utils.getVecArraySize(dims)
  val file = {
    val f = new File("./src/test/resources/testFile")
    if(f.exists()) f.delete()
    f
  }
  val importer = new VecArrayImporter(file, dims)

  val generator = VectorGenerator
  val vecArray: Array[Array[Float]] = new Array[Int](vecCountNum).map(item => generator.genVecArray(dims))
}

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class VecArrayImporterTest {
  @Test
  def test1(): Unit = {
    val exporter = new VecExporter(file)
    Assert.assertEquals(vecCountNum, vecArray.length)
    val iter = vecArray.iterator
    exporter.writeVec(iter.next())
    Assert.assertEquals(stepLength, file.length())
    exporter.writeVec(iter.next())
    Assert.assertEquals(file.length(), stepLength*2)
    exporter.writeBatch(iter)
    Assert.assertEquals(file.length(), stepLength*vecCountNum)
  }

  @Test
  def test2(): Unit = {
    val randomIndex = Random.nextInt(vecCountNum)
    val vec = importer.getVec(randomIndex)
    Assert.assertArrayEquals(vecArray(randomIndex), vec, 0.0001f)
  }

  @Test
  def test3(): Unit = {
    val iter1 = importer.getVecArray
    val iter2 = vecArray.iterator
    while (iter1.hasNext) {
      val vec1 = iter1.next()
      val vec2 = iter2.next()
      Assert.assertArrayEquals(vec2, vec1, 0.001f)
    }
  }

}
