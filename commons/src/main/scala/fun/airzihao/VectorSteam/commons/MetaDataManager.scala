package fun.airzihao.VectorSteam.commons

import java.util.concurrent.atomic.AtomicLong

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:22 2021/1/4
 * @Modified By:
 */
object MetaDataManager {
  private val _moleculeIdAllocator: AtomicLong = new AtomicLong(0)
  def availableMoleculeId: Long = _moleculeIdAllocator.getAndIncrement()

}
