package fun.airzihao.VectorSteam.commons

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 11:17 2021/1/6
 * @Modified By:
 */
trait Spliter {
  val partitionCount: Int
  def getHashValue(mole: VecMolecule): Array[Boolean]
}
