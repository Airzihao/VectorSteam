package fun.airzihao.VectorSteam.commons

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 15:54 2021/1/6
 * @Modified By:
 */
object VecUtils {
  def halfReverse(pureVec: PureVec): PureVec = {
    val vec = pureVec.vec.zipWithIndex.map(item => {
      if(item._2 % 2 == 1) -item._1
      else item._1
    })
    PureVec(vec)
  }

}
