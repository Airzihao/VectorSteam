package fun.airzihao.VectorSteam.Utils

import java.io.{BufferedOutputStream, FileOutputStream}
import java.io.File

import fun.airzihao.VectorSteam.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.kernel.VecMolecule

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

  def close(): Unit = {
    fos.close()
  }
}
