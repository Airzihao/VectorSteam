package fun.airzihao.VectorSteam.IOUtils

import java.io.{BufferedOutputStream, FileOutputStream}
import java.io.File

import fun.airzihao.VectorSteam.commons.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.commons.VecMolecule

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:21 2021/1/4
 * @Modified By:
 */
class SteamExporter (file: File) {
  val fos: BufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))

  def writeVecMolecule(vecMolecule: VecMolecule) = {
    fos.write(VectorSerializer.serialize(vecMolecule))
    fos.flush()
  }

  def writeBatch(iter: Iterator[VecMolecule]) = {
    iter.foreach(molecule => fos.write(VectorSerializer.serialize(molecule)))
    fos.flush()
  }

}

// The following class may be used in tests.
class VecExporter (file: File) {
  val fos: BufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))

  def writeVec(vec: Array[Float]) = {
    fos.write(VectorSerializer.serialize(vec))
    fos.flush()
  }

  def writeBatch(iter: Iterator[Array[Float]]) = {
    iter.foreach(vec => fos.write(VectorSerializer.serialize(vec)))
    fos.flush()
  }

}