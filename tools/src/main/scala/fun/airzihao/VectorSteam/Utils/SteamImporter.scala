package fun.airzihao.VectorSteam.Utils

import java.io.{BufferedInputStream, DataInputStream, File, FileInputStream, FileReader, InputStreamReader}
import java.nio.{ByteBuffer, MappedByteBuffer}
import java.nio.channels.FileChannel

import fun.airzihao.VectorSteam.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.kernel.VecMolecule

import scala.collection.mutable.ArrayBuffer

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:20 2021/1/4
 * @Modified By:
 */
class SteamImporter(file: File, dims: Int) {
  val stepLength: Int = Utils.vecLength(dims)

  def getVecMolecules: Iterator[VecMolecule] = {
    val bis = new BufferedInputStream(new FileInputStream(file), 10 * 1024 * 1024)
    new VecMoleculeIter(bis)
  }

  def getVecMolecule(index: Int): VecMolecule = {
    val fis = new BufferedInputStream(new FileInputStream(file))
    val bytes = new Array[Byte](stepLength)
    for (i<-0 until index) fis.read(bytes)
    fis.read(bytes)
    VectorSerializer.deserializeVecMolecule(bytes)
  }

  class VecMoleculeIter(fis: BufferedInputStream)extends Iterator[VecMolecule] {
    override def hasNext: Boolean = (fis.available() >= stepLength)

    override def next(): VecMolecule = {
      val bytes = new Array[Byte](stepLength)
      fis.read(bytes)
      VectorSerializer.deserializeVecMolecule(bytes)
    }
  }

}


