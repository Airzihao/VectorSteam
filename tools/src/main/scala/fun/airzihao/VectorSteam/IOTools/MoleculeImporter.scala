package fun.airzihao.VectorSteam.IOTools

import java.io.{BufferedInputStream, File, FileInputStream}
import fun.airzihao.VectorSteam.commons.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.commons.Utils.BasicUtils
import fun.airzihao.VectorSteam.commons.{VecMolecule, VecMoleculeIter}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:20 2021/1/4
 * @Modified By:
 */
class MoleculeImporter(file: File, dims: Int) extends SteamImporter {
  override val srcFile: File = file
  override val stepLength: Int = BasicUtils.getVecMoleculeSize(dims)

  def getVecMolecules: Iterator[VecMolecule] = {
    val bis = new BufferedInputStream(new FileInputStream(file), 10 * 1024 * 1024)
    new VecMoleculeIter(bis, stepLength)
  }

  def getVecMolecule(index: Int): VecMolecule = {
    val bytes = _getBytesByIndex(index)
    VectorSerializer.deserializeVecMolecule(bytes)
  }

}



