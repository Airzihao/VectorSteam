package fun.airzihao.VectorSteam.Utils

import java.io.{BufferedInputStream, File, FileInputStream}

import fun.airzihao.VectorSteam.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.kernel.VecMolecule
import fun.airzihao.VectorSteam.commons.Utils

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:20 2021/1/4
 * @Modified By:
 */
class MoleculeImporter(file: File, dims: Int) extends SteamImporter {
  override val srcFile: File = file
  override  val stepLength: Int = Utils.getVecMoleculeSize(dims)

  def getVecMolecules: Iterator[VecMolecule] = {
    val bis = new BufferedInputStream(new FileInputStream(file), 10 * 1024 * 1024)
    new VecMoleculeIter(bis)
  }

  def getVecMolecule(index: Int): VecMolecule = {
    val bytes = _getBytesByIndex(index)
    VectorSerializer.deserializeVecMolecule(bytes)
  }


  class VecMoleculeIter(fis: BufferedInputStream) extends SteamIter[VecMolecule](fis) {

    override def next(): VecMolecule = {
      fis.read(bytes)
      VectorSerializer.deserializeVecMolecule(bytes)
    }
  }

}



