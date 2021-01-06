package fun.airzihao.VectorSteam.commons

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 11:23 2021/1/4
 * @Modified By:
 */

//
trait Vec {
  val vec: Seq[Float]
}
case class PureVec(vec: Seq[Float])
case class VecMolecule(id: Long, vec: Array[Float]){
}