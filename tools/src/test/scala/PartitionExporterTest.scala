import java.io.File

import PartitionExporterTest.{partitionExporter, splitVecs}
import fun.airzihao.VectorSteam.IOUtils.{MoleculeImporter, PartitionExporter, SteamExporter}
import fun.airzihao.VectorSteam.clustering.RandomForestSpliter
import fun.airzihao.VectorSteam.commons.{PureVec, VecMolecule}
import fun.airzihao.VectorSteam.commons.VecUtils.halfReverse
import org.junit.Test

import scala.util.Random

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:03 2021/1/6
 * @Modified By:
 */
object PartitionExporterTest {
  val inputFile = new File("./src/test/resources/sift1m-mole")
  val outputPath = "./src/test/resources/partition-output"
  val importer = new MoleculeImporter(inputFile, 128)
  val splitVecs = new Array[Int](5).zipWithIndex.map(item => halfReverse(PureVec(importer.getVecMolecule(item._2 + Random.nextInt(100)).vec)))
  val spliter = new RandomForestSpliter(splitVecs)
  val partitionExporter = new PartitionExporter(spliter, importer.getVecMolecules, outputPath)

}

class PartitionExporterTest {

  @Test
  def test1(): Unit = {
    println("start to export.")
    partitionExporter.`export`()
    val ex = new SteamExporter(new File("./src/test/resources/splitVecs-5"))
    splitVecs.map(item => VecMolecule(-1, item.vec.toArray)).foreach(vec => ex.writeVecMolecule(vec))
  }

}
