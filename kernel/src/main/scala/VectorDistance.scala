/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 21:31 2020/12/1
 * @Modified By:
 */
trait VectorSim {

}

object CosineDis {

}

object EuclideanDis {
  def eucDis = ???
  def stdEucDis = ???
  def weightedEucDis = ???
  def eucLength(vector: Vector[Double]): Double = {
    var squareSum: Double = 0
    vector.foreach(item => squareSum += item * item)
    math.sqrt(squareSum)
  }
}

object MinkowskiDis {

}

object JaccardDis {
//  Jaccard similarity coefficient
}

object HammingDis {

}

object ChebyshevDis {
//  max( | x2-x1 | , | y2-y1 | )
}

object ManhattanDis {
//  max( | x2-x1 | , | y2-y1 | )
}