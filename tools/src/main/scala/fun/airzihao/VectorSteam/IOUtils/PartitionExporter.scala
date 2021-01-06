package fun.airzihao.VectorSteam.IOUtils

import java.io.File

import fun.airzihao.VectorSteam.commons.{BasicTypeTransformer, Spliter, VecMolecule}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:30 2021/1/6
 * @Modified By:
 */
class PartitionExporter(spliter: Spliter, iter: Iterator[VecMolecule], path: String) {

  private val _pathAvailable: Boolean = {
    val pathFile = new File(path)
    val _isEmptyDir: Boolean = pathFile.isDirectory && pathFile.listFiles().length == 0
    val _notExists: Boolean = !pathFile.exists()
    if (_notExists) pathFile.mkdirs()
    else _isEmptyDir
  }

  if(!_pathAvailable) throw new Exception(s"$path is not an empty directory.")
  val exportMap: Map[Int, SteamExporter] = new Array[Int](spliter.partitionCount).zipWithIndex.map(item => {
    val exporter = new SteamExporter(new File(s"$path/${item._2}"))
    (item._2 -> exporter)
  }).toMap

  def export(): Unit = {
    iter.foreach(mole => {
      val hashValue = spliter.getHashValue(mole)
      val partId: Int = BasicTypeTransformer.boolArr2Int(hashValue)
      exportMap(partId).writeVecMolecule(mole)
    })
  }


}
