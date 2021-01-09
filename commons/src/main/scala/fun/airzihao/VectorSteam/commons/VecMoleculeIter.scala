package fun.airzihao.VectorSteam.commons

import java.io.BufferedInputStream
import fun.airzihao.VectorSteam.commons.Serializer.VectorSerializer

import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

class MultiThreadVecMoleIter(bis: BufferedInputStream, stepLength: Int) extends Iterator[VecMolecule] {
  val bytesQueue: BlockingQueue[Array[Byte]] = new LinkedBlockingQueue[Array[Byte]](1000)
  val moleQueue: BlockingQueue[VecMolecule] = new LinkedBlockingQueue[VecMolecule](1000)
  val readBytes = Future{
    while (bis.available() >= stepLength) {
      val bytes = new Array[Byte](stepLength)
      bis.read((bytes))
      bytesQueue.put(bytes)
    }
  }

  val filler1 = Future {
    _fillMoleQueue
  }

  val filler2 = Future {
    _fillMoleQueue
  }

  val filler3 = Future {
    _fillMoleQueue
  }

  private def _fillMoleQueue: Unit = {
    val bytes = bytesQueue.take()
    moleQueue.put(VectorSerializer.deserializeVecMolecule(bytes))
  }

  override def hasNext: Boolean = bis.available() >= stepLength || moleQueue.remainingCapacity()>0
  override def next(): VecMolecule = ???
}

abstract class SteamIter[T](is: BufferedInputStream, stepLength: Int) extends Iterator[T] {

  override def hasNext: Boolean = is.available() >= stepLength
  val bytes = new Array[Byte](stepLength)
  override def next(): T;
}
