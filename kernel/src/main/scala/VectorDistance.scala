/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 21:31 2020/12/1
 * @Modified By:
 */
trait VectorSim {

}


object CosineSim {
// cosSim = (a*b)/(|a|*|b|)
  def cosineSim(vector1: Vector[Double], vector2: Vector[Double]): Double = {
    val numerator: Double = vector1.zip(vector2).map(pair => pair._1*pair._2).sum
    val denominator: Double = EuclideanDis.eucLength(vector1) * EuclideanDis.eucLength(vector2)
    numerator / denominator
  }
}

object EuclideanDis {
  def eucDis(vector1: Vector[Double], vector2: Vector[Double]): Double = {
    val squareSum = vector1.zip(vector2).map( pair => math.pow((pair._1 - pair._2), 2)).sum
    math.sqrt(squareSum)
  }

  def stdEucDis(vector1: Vector[Double], vector2: Vector[Double]): Double = {
    0.0
  }

  def weightedEucDis = ???

  def eucLength(vector: Vector[Double]): Double = {
//    vector.foreach(item => squareSum += item * item)
    val squareSum: Double = vector.map(elem => elem*elem).sum
    math.sqrt(squareSum)
  }
}

object JaccardSim {
//  Jaccard similarity coefficient
}

object HammingDis {

}

object MinkowskiDis {

}

object ChebyshevDis {
  //  max( | x2-x1 | , | y2-y1 | )
  def chebyDis(vector1: Vector[Double], vector2: Vector[Double]): Double = {
    vector1.zip(vector2).map(pair => math.abs(pair._1 - pair._2)).max
  }
}

object ManhattanDis {
//  sum( | x2-x1 | , | y2-y1 | )
  def manhattanDis(vector1: Vector[Double], vector2: Vector[Double]): Double = {
    vector1.zip(vector2).map(pair => math.abs(pair._1 - pair._2)).sum
  }
}