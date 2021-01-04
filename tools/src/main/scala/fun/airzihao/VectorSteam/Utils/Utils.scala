package fun.airzihao.VectorSteam.Utils

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 10:26 2020/12/7
 * @Modified By:
 */


object Utils {
  def timing[T](f: => T): T = {
    val t1 = System.currentTimeMillis()
    val t = f
    val t2 = System.currentTimeMillis()
    //    logger.debug(s"time cost: ${t2 - t1} ms")
    println(s"time cost: ${t2 - t1} ms")
    t
  }

  def vecLength(dims: Int): Int = {
    // id:Long, len: Int, arr[Float]
    8 + 4 + 4*dims
  }
}
