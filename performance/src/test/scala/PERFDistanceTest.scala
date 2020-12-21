//
//
///**
// * @Author: Airzihao
// * @Description:
// * @Date: Created at 16:28 2020/12/15
// * @Modified By:
// */
//class PERFDistanceTest extends TestBase {
//
//  def perfEucLength(count: Int, dim: Int): Unit = {
//    val tempArr = new Array[Int](count)
//    val vecArr: Array[Vector[Double]] = tempArr.map(item => VectorGenerator.genVector(dim))
//    println("generated")
//    timing(vecArr.foreach(vec => EuclideanDis.eucLength(vec)))
//  }
//
//  def perfEucDis(count: Int, dim: Int): Unit = {
//    val v1 = VectorGenerator.genVector(dim)
//    val v2 = VectorGenerator.genVector(dim)
//    for(i <- 1 to count) EuclideanDis.eucDis(v1, v2)
//  }
//
//}
