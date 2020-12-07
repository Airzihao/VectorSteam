/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:29 2020/12/7
 * @Modified By:
 */
class TestBase {
  def timing[T](f: => T): T = {
    val t1 = System.currentTimeMillis()
    val t = f
    val t2 = System.currentTimeMillis()
//    logger.debug(s"time cost: ${t2 - t1} ms")
    println(s"time cost: ${t2 - t1} ms")
    t
  }

}
