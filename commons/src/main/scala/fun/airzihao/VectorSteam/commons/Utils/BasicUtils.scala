package fun.airzihao.VectorSteam.commons.Utils

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 10:26 2020/12/7
 * @Modified By:
 */


object BasicUtils {
  def timing[T](f: => T): T = {
    val t1 = System.currentTimeMillis()
    val t = f
    val t2 = System.currentTimeMillis()
    //    logger.debug(s"time cost: ${t2 - t1} ms")
    println(s"time cost: ${t2 - t1} ms")
    t
  }

  def timing[T](message: String, f: => T): T = {
    val t1 = System.currentTimeMillis()
    val t = f
    val t2 = System.currentTimeMillis()
    //    logger.debug(s"time cost: ${t2 - t1} ms")
    println(s"$message time cost: ${t2 - t1} ms")
    t
  }

  def getVecMoleculeSize(dims: Int): Int = 8 + 4 + 4 * dims

  def getVecArraySize(dims: Int): Int = 4 + 4 * dims
}
