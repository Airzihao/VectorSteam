package fun.airzihao.VectorSteam.search

import java.io.{BufferedInputStream, File, FileInputStream}
import fun.airzihao.VectorSteam.commons.{Utils, _}

/**
 * @Author: Airzihao
 * @Description:
 * @Date: Created at 13:29 2021/1/5
 * @Modified By:
 */
object SearchEngine {

  var path: String = ""

  def setSearchPath(p: String) = path = p

  def getNearestMolecule(molecule: VecMolecule, spliter: Spliter, path: String,
                         measureFunc:(Array[Float], Array[Float]) => Float, orderMethod: OrderMethod): VecMolecule = {
    getKNearestMolecules(molecule, spliter, path, measureFunc, orderMethod, 1).head
  }

  def getKNearestMolecules(molecule: VecMolecule, spliter: Spliter, path: String,
                         measureFunc:(Array[Float], Array[Float]) => Float, orderMethod: OrderMethod, k: Int): Array[VecMolecule] = {
    val partId: Int = spliter.getPartitionId(molecule)
    println(partId)
    val stepLength = Utils.BasicUtils.getVecMoleculeSize(molecule.vec.length)
    val bis = new BufferedInputStream(new FileInputStream(new File(s"$path/$partId")))
    val iter = new VecMoleculeIter(bis, stepLength)
    getKNearestMolecules(molecule, iter, measureFunc, orderMethod, k)
  }


  def getKNearestMolecules(molecule: VecMolecule, iter: Iterator[VecMolecule],
                          measureFunc:(Array[Float], Array[Float]) => Float, orderMethod: OrderMethod, k: Int): Array[VecMolecule] = {
    val sorter: SortedVecMolecules = new SortedVecMolecules(k, orderMethod)
    iter.foreach(mole => sorter.tryToInsert((measureFunc(mole.vec, molecule.vec), mole)))
    sorter.sortedMolecules
  }

  def getNearestMolecule(molecule: VecMolecule, iter: Iterator[VecMolecule],
                         measureFunc:(Array[Float], Array[Float]) => Float, orderMethod: OrderMethod): VecMolecule = {
    getKNearestMolecules(molecule, iter, measureFunc, orderMethod, 1).head
  }

  def getKNearestMolecules(molecule: VecMolecule, dataset: Iterable[VecMolecule],
                           measureFunc:(Array[Float], Array[Float]) => Float, orderMethod: OrderMethod, k: Int): Array[VecMolecule] = {

    val disArray: Array[(Float, Int)] = dataset.zipWithIndex.map(pair => (measureFunc(molecule.vec, pair._1.vec), pair._2)).toArray

    //sortBy: defult to be asending
    val sortedArray = orderMethod match {
      case Asending => disArray.sortBy(item => item._1)
      case Desending => disArray.sortBy(item => item._1).reverse
    }

    val dataArray: Array[VecMolecule] = dataset.toArray
    if(k >= sortedArray.length) sortedArray.map(item => dataArray(item._2))
    else sortedArray.slice(0,k).map(item => dataArray(item._2))
  }

  def getNearestMolecule(molecule: VecMolecule, dataset: Iterable[VecMolecule],
                         measureFunc:(Array[Float], Array[Float]) => Float, orderMethod: OrderMethod): VecMolecule = {
    orderMethod match {
      case Asending => dataset.minBy(mole => measureFunc(molecule.vec, mole.vec))
      case Desending => dataset.maxBy(mole => measureFunc(molecule.vec, mole.vec))
    }
  }
}
