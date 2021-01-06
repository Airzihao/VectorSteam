import fun.airzihao.VectorSteam.commons.VecMolecule
import org.junit.Assert

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 14:28 2021/1/5
 * @Modified By:
 */
object TestBase {

  def molesEqual(mole1: VecMolecule, mole2: VecMolecule): Unit = {
    Assert.assertEquals(mole1.id, mole2.id)
    Assert.assertArrayEquals(mole1.vec, mole2.vec, 0.001f)
  }
}
