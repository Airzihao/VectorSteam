package fun.airzihao.VectorSteam.Serializer
import fun.airzihao.VectorSteam.commons.Serializer.VectorSerializer
import fun.airzihao.VectorSteam.commons.{VecMolecule, VectorGenerator}
import fun.airzihao.VectorSteam.commons.Utils.timing
import org.junit.runners.MethodSorters
import org.junit.{Assert, FixMethodOrder, Test}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:59 2020/12/22
 * @Modified By:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class VectorSerializerTest {
  val serializer = VectorSerializer
  val vec: Array[Float] = VectorGenerator.genVecArray(256)
  val molecule: VecMolecule = VecMolecule(0, vec)

  @Test
  def correctTest1(): Unit = {
    val bytes = serializer.serialize(vec)
    val vec2: Array[Float] = serializer.deserializeArray(bytes)
    Assert.assertArrayEquals(vec, vec2, 0.0001f)
  }

  @Test
  def correctTest2(): Unit = {
    val bytes: Array[Byte] = serializer.serialize(molecule)
    val mole = serializer.deserializeVecMolecule(bytes)
    Assert.assertEquals(molecule.id, mole.id)
    Assert.assertArrayEquals(molecule.vec, mole.vec, 0.0001f)
  }

  @Test
  def perfTest(): Unit = {
    val bytesArray = serializer.serialize(vec)
    val bytesVecMolecule = serializer.serialize(molecule)
    val repeatCount: Int = 1000000
    val f1 = Future{
      println("serialize-array")
      timing(for(i<-1 to repeatCount) serializer.serialize(vec))
    }
    val f2 = Future{
      println("deserialize-array")
      timing(for(i<-1 to repeatCount) serializer.deserializeArray(bytesArray))
    }
    Await.ready(f1, 10.seconds)
    Await.ready(f2, 10.seconds)
    val f3 = Future{
      println("serialize-mole")
      timing(for(i<-1 to repeatCount) serializer.serialize(molecule))
    }
    val f4 = Future{
      println("deserialize-mole")
      timing(for(i<-1 to repeatCount) serializer.deserializeVecMolecule(bytesVecMolecule))
    }
    Await.ready(f3, 10.seconds)
    Await.ready(f4, 10.seconds)
  }

}
