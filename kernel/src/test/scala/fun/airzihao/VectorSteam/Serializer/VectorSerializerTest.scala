package fun.airzihao.VectorSteam.Serializer
import fun.airzihao.VectorSteam.Utils.Utils.timing
import fun.airzihao.VectorSteam.Utils.VectorGenerator
import org.junit.{Assert, Test}

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:59 2020/12/22
 * @Modified By:
 */
class VectorSerializerTest {
  val serializer = VectorSerializer
  val vec: Array[Double] = VectorGenerator.genVecArray(256)

  @Test
  def correctTest(): Unit = {
    val bytes = serializer.serialize(vec)
    val vec2: Array[Double] = serializer.deserializeArray(bytes)
    Assert.assertArrayEquals(vec, vec2, 0.0001)
  }

  @Test
  def perfTest(): Unit = {
    val bytes = serializer.serialize(vec)
    val repeatCount: Int = 1000000
    val f1 = Future{
      println("serialize")
      timing(for(i<-1 to repeatCount) serializer.serialize(vec))
    }
    val f2 = Future{
      println("deserialize")
      timing(for(i<-1 to repeatCount) serializer.deserializeArray(bytes))
    }
    Await.ready(f1, 10.seconds)
    Await.ready(f2, 10.seconds)
  }

}
