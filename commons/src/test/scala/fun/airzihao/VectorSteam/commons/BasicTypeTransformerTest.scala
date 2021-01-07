package fun.airzihao.VectorSteam.commons

import fun.airzihao.VectorSteam.commons.Utils.BasicTypeTransformer
import org.junit.{Assert, Test}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 12:28 2021/1/6
 * @Modified By:
 */
class BasicTypeTransformerTest {

  val hashValue1 = Array(true, false, false, true)
  @Test
  def test1(): Unit = {
    Assert.assertEquals("1001", BasicTypeTransformer.boolArr2String(hashValue1))
  }
  @Test
  def test2(): Unit = {
    Assert.assertEquals(9, BasicTypeTransformer.boolArr2Int(hashValue1))
  }

}
