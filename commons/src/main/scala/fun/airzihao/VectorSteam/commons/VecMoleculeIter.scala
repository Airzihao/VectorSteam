package fun.airzihao.VectorSteam.commons

import java.io.BufferedInputStream

import fun.airzihao.VectorSteam.commons.Serializer.VectorSerializer

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 15:44 2021/1/5
 * @Modified By:
 */
class VecMoleculeIter(bis: BufferedInputStream, stepLength: Int) extends SteamIter[VecMolecule](bis, stepLength) {

  override def next(): VecMolecule = {
    bis.read(bytes)
    VectorSerializer.deserializeVecMolecule(bytes)
  }
}

abstract class SteamIter[T](is: BufferedInputStream, stepLength: Int) extends Iterator[T] {

  override def hasNext: Boolean = is.available() >= stepLength
  val bytes = new Array[Byte](stepLength)
  override def next(): T;
}
