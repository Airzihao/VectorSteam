package fun.airzihao.VectorSteam.commons

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 11:32 2021/1/6
 * @Modified By:
 */
object BasicTypeTransformer {

  def boolArr2String(boolArr: Array[Boolean]): String = {
    boolArr.map(bool => bool match {
      case true => 1
      case false => 0
    }).mkString
  }

  def boolArr2Int(boolArr: Array[Boolean]): Int = {
    Integer.parseInt(boolArr2String(boolArr), 2)
  }
}
