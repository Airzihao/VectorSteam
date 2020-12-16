package fun.airzihao.VectorDistance

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 16:42 2020/12/15
 * @Modified By:
 */

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
