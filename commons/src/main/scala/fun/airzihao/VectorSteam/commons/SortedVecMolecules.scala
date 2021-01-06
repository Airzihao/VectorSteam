package fun.airzihao.VectorSteam.commons

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 19:20 2021/1/5
 * @Modified By:
 */
class SortedVecMolecules(maxSize: Int, orderMethod: OrderMethod) {
  private var _sortedResultArray: Array[(Float, VecMolecule)] = new Array[(Float, VecMolecule)](0)
    //.map(_ => (0f, VecMolecule(-1, Array.emptyFloatArray)))
  // sortBy, default to be asending.
  private def _sort: Unit = {
    _sortedResultArray = orderMethod match {
      case Asending => _sortedResultArray.sortBy(item => item._1)
      case Desending => _sortedResultArray.sortBy(item => item._1).reverse
    }
  }

  def tryToInsert(disPair:(Float, VecMolecule)): Unit = {
    if(_sortedResultArray.length >= maxSize) {
      orderMethod match {
        case Asending => if(disPair._1 < _sortedResultArray.last._1) _insertToFullArray(disPair)
        case Desending => if(disPair._1 >  _sortedResultArray.last._1) _insertToFullArray(disPair)
      }

    } else {
      _fillHungryArray(disPair)
    }
  }
  private def _insertToFullArray(disPair: (Float, VecMolecule)): Unit = {
    _sortedResultArray = (disPair +: _sortedResultArray.dropRight(1))
    _sort
  }

  private def _fillHungryArray(disPair: (Float, VecMolecule)): Unit = {
    _sortedResultArray = disPair +: _sortedResultArray
    _sort
  }

  def sortedMolecules: Array[VecMolecule] = _sortedResultArray.map(item => item._2)
  def sortedMoleculesWithDistance: Array[(Float, VecMolecule)] = _sortedResultArray

}
