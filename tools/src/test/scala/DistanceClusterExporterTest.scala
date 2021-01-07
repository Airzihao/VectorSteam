import DistanceClusterExporterTest.{partitionExporter, splitVecs}
import fun.airzihao.VectorSteam.IOTools.{MoleculeImporter, PartitionExporter, SteamExporter}
import fun.airzihao.VectorSteam.clustering.{DistanceCluster, RandomForestSpliter}
import fun.airzihao.VectorSteam.commons.{PureVec, VecMolecule}
import org.junit.Test

import java.io.File

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 11:32 2021/1/7
 * @Modified By:
 */
object DistanceClusterExporterTest {
  val dims: Int = 128
  val inputFile = new File("./src/test/resources/sift1m-mole")
  val outputPath = "./src/test/resources/part-disCluster"
  val importer = new MoleculeImporter(inputFile, 128)
  val splitVecs = new Array[Int](100).zipWithIndex.map(item =>VecMolecule(item._2.toLong, importer.getVecMolecule(item._2).vec))
  val spliter = new DistanceCluster(splitVecs)
  val partitionExporter = new PartitionExporter(spliter, importer.getVecMolecules, outputPath)
}
class DistanceClusterExporterTest {

  @Test
  def test1(): Unit = {
    println("start to export.")
    partitionExporter.`export`()
    val ex = new SteamExporter(new File("./src/test/resources/splitVecs-100"))
    ex.writeBatch(splitVecs.iterator)

  }
}
