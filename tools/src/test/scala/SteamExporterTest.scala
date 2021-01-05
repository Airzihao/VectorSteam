import fun.airzihao.VectorSteam.Utils.{FvecFileImporter, MoleculeImporter, SteamExporter, VecArrayImporter}
import java.io.File

import SteamExporterTest.{dims, exporter, importer, inputFile, outputFile}
import fun.airzihao.VectorSteam.commons.{MetaDataManager, VecMolecule, VecMoleculeIter}
import org.junit.runners.MethodSorters
import org.junit.{Assert, FixMethodOrder, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 15:57 2021/1/5
 * @Modified By:
 */
object SteamExporterTest {
  val dims: Int = 128
  val outputFile: File = new File("./src/test/resources/sift1m-mole")
  val inputFile: File = new File("./src/test/resources/sift_base.fvecs")
  val exporter: SteamExporter = new SteamExporter(outputFile)
  val importer: FvecFileImporter = new FvecFileImporter(inputFile)
}

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SteamExporterTest {
  @Test
  def test1(): Unit ={
    val vecArrayIter: Iterator[Array[Float]] = importer.getVecArray
    while (vecArrayIter.hasNext) {
      exporter.writeVecMolecule(VecMolecule(MetaDataManager.availableMoleculeId, vecArrayIter.next()))
    }
  }

  @Test
  def test2(): Unit = {
    val iter1: Iterator[VecMolecule] = new MoleculeImporter(outputFile, dims).getVecMolecules
    val iter2: Iterator[Array[Float]] = new FvecFileImporter(inputFile).getVecArray
    iter1.zip(iter2).foreach(pair => Assert.assertArrayEquals(pair._1.vec, pair._2, 0.001f))
  }

}
