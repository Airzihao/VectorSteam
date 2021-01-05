package fun.airzihao.VectorSteam.Utils

import java.io.{BufferedInputStream, File, FileInputStream}

/**
  * @Author: Airzihao
  * @Description:
  * @Date: Created in 21:56 2021/1/4
  * @Modified By:
  */
trait SteamImporter {
  val srcFile: File;
  val stepLength: Int;

  protected def _getBytesByIndex(index: Int): Array[Byte] = {
    val fis = new BufferedInputStream(new FileInputStream(srcFile))
    val bytes = new Array[Byte](stepLength)
    for (i<-0 until index) fis.read(bytes)
    fis.read(bytes)
    bytes
  }
}

