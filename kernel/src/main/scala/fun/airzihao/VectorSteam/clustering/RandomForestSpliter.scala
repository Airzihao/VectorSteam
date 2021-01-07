package fun.airzihao.VectorSteam.clustering

import fun.airzihao.VectorSteam.VectorDistance.CosineSim
import fun.airzihao.VectorSteam.commons.Utils.BasicTypeTransformer
import fun.airzihao.VectorSteam.commons.{PureVec, Spliter, VecMolecule}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 10:14 2021/1/6
 * @Modified By:
 */
class RandomForestSpliter(splitVecs: Seq[PureVec]) extends Spliter {
  val spliterCount = splitVecs.length
  override val partitionCount: Int = math.pow(2, spliterCount).toInt


  override def getPartitionId(mole: VecMolecule): Int = {
    BasicTypeTransformer.boolArr2Int(getHashValue(mole))
  }

  def getHashValue(mole: VecMolecule): Array[Boolean] = {
    splitVecs.map(splitVec => {
      val value = CosineSim.cosineSim(splitVec.vec, mole.vec)
      if(value >= 0) true
      else false
    }).toArray
  }

}
