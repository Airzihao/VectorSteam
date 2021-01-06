package fun.airzihao.VectorSteam.clustering

import fun.airzihao.VectorSteam.commons.VecMolecule

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 9:51 2021/1/5
 * @Modified By:
 */
object ClusterUtils {
  def getSpaceBound(iter: Iterator[VecMolecule]): Array[(Float, Float)] = {
    var bound: Array[(Float, Float)] = iter.next().vec.map(item => (item, item))
    iter.foreach(molecule => bound = _modifyBound(molecule, bound))
    bound
  }

  private def _modifyBound(vecMolecule: VecMolecule, initBound: Array[(Float, Float)]): Array[(Float, Float)] = {
    vecMolecule.vec.zip(initBound).map(item => {
      val lowerBound = math.min(item._1, item._2._1)
      val upperBound = math.max(item._1, item._2._2)
      (lowerBound, upperBound)
    })
  }
}
