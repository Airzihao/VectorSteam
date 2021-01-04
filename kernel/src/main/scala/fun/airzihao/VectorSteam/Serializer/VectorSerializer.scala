package fun.airzihao.VectorSteam.Serializer

import fun.airzihao.VectorSteam.kernel.VecMolecule
import io.netty.buffer.{ByteBuf, ByteBufAllocator, Unpooled}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 10:22 2020/12/22
 * @Modified By:
 */
object VectorSerializer {
  val allocator: ByteBufAllocator = ByteBufAllocator.DEFAULT

  def serialize(vec: Array[Double]): Array[Byte] = {
    val byteBuf: ByteBuf = allocator.buffer()
    byteBuf.writeInt(vec.length)
    vec.foreach(byteBuf.writeDouble(_))
    val bytes = _exportBytes(byteBuf)
    byteBuf.release()
    bytes
  }

  def serialize(vecMolecule: VecMolecule): Array[Byte] = {
    val byteBuf: ByteBuf = allocator.buffer()
    byteBuf.writeLong(vecMolecule.id)
    byteBuf.writeInt(vecMolecule.vec.length)
    vecMolecule.vec.foreach(byteBuf.writeDouble(_))
    val bytes = _exportBytes(byteBuf)
    byteBuf.release()
    bytes
  }

  def deserializeArray(bytes: Array[Byte]): Array[Double] = {
    val byteBuf: ByteBuf = Unpooled.wrappedBuffer(bytes)
    val length: Int = byteBuf.readInt()
    val array: Array[Double] = new Array[Double](length)
    val vec: Array[Double] = array.map(_ => byteBuf.readDouble())
    byteBuf.release()
    vec
  }

  def deserializeVecMolecule(bytes: Array[Byte]): VecMolecule = {
    val byteBuf: ByteBuf = Unpooled.wrappedBuffer(bytes)
    val id: Long = byteBuf.readLong()
    val length: Int = byteBuf.readInt()
    val array: Array[Double] = new Array[Double](length)
    val vec: Array[Double] = array.map(_ => byteBuf.readDouble())
    byteBuf.release()
    VecMolecule(id, vec)
  }

  private def _exportBytes(byteBuf: ByteBuf): Array[Byte] = {
    val dst = new Array[Byte](byteBuf.writerIndex())
    byteBuf.readBytes(dst)
    dst
  }
}
