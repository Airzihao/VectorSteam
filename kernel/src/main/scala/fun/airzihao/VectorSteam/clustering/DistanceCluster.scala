package fun.airzihao.VectorSteam.clustering

import fun.airzihao.VectorSteam.VectorDistance.EuclideanDis
import fun.airzihao.VectorSteam.commons.{PureVec, Spliter, VecMolecule}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 9:25 2021/1/7
 * @Modified By:
 */

// take the VecMolecule's id as the cluster serialNum.
class DistanceCluster(kernelMoles: Seq[VecMolecule]) extends Spliter {
  override val partitionCount: Int = kernelMoles.length

  override def getPartitionId(mole: VecMolecule): Int = {
    kernelMoles.minBy(kernelMole => EuclideanDis.eucDis(kernelMole.vec, mole.vec)).id.toInt
  }
}
