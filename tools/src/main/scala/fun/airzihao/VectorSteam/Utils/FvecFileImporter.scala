package fun.airzihao.VectorSteam.Utils

import java.io.{BufferedInputStream, File, FileInputStream}

import fun.airzihao.VectorSteam.commons.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.commons.{SteamIter, Utils}
import io.netty.buffer.Unpooled

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 17:04 2021/1/5
 * @Modified By:
 */
class FvecFileImporter(file: File) {
  val bis: BufferedInputStream = new BufferedInputStream(new FileInputStream(file))
  val dims: Int = {
    val bytes = new Array[Byte](4)
    bis.read(bytes)
    Unpooled.wrappedBuffer(bytes).readIntLE()
  }
  val stepLength: Int = Utils.getVecArraySize(dims)

  def getVecArray: Iterator[Array[Float]] = {
    new VecArrayIter(bis, stepLength)
  }

  def getVec(index: Int): Array[Float] = {
    val bytes = _getBytesByIndex(index)
    VectorSerializer.deserializeArray(bytes)
  }

  class VecArrayIter(bis: BufferedInputStream, stepLength: Int) extends SteamIter[Array[Float]](bis, stepLength) {
    def next(): Array[Float] = {
      val bytes = new Array[Byte](stepLength)
      bis.read(bytes)
      VectorSerializer.deserializeFvecArray(bytes, dims)
    }
  }

  protected def _getBytesByIndex(index: Int): Array[Byte] = {
    val fis = new BufferedInputStream(new FileInputStream(file))
    val bytes = new Array[Byte](stepLength)
    for (i<-0 until index) fis.read(bytes)
    fis.read(bytes)
    bytes
  }

}