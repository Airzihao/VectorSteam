package fun.airzihao.VectorSteam.Utils

import java.io.{BufferedInputStream, File, FileInputStream}

import fun.airzihao.VectorSteam.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.commons.Utils

/**
  * @Author: Airzihao
  * @Description:
  * @Date: Created in 21:55 2021/1/4
  * @Modified By:
  */
class VecArrayImporter(file: File, dims: Int) extends SteamImporter {
  override val srcFile: File = file
  val stepLength: Int = Utils.getVecArraySize(dims)

  def getVecArray: Iterator[Array[Float]] = {
    val bis = new BufferedInputStream(new FileInputStream(file), 10*1024*1024)
    new VecArrayIter(bis)
  }

  def getVec(index: Int): Array[Float] = {
    val bytes = _getBytesByIndex(index)
    VectorSerializer.deserializeArray(bytes)
  }

  class VecArrayIter(bis: BufferedInputStream) extends SteamIter[Array[Float]](bis) {
    def next(): Array[Float] = {
      bis.read(bytes)
      VectorSerializer.deserializeArray(bytes)
    }
  }
}