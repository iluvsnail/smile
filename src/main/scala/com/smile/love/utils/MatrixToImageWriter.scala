package com.smile.love.utils

import java.awt.image.BufferedImage
import java.io.{File, IOException, OutputStream}
import javax.imageio.ImageIO

import com.google.zxing.common.BitMatrix

/**
  * User: chenyl 
  * Date: 2017-07-26  12:42 
  */
object MatrixToImageWriter {
  private val BLACK = 0xFF000000
  private val WHITE = 0xFFFFFFFF


  def toBufferedImage(matrix: BitMatrix): BufferedImage = {
    val width = matrix.getWidth
    val height = matrix.getHeight
    val image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    var x = 0
    while ( {
      x < width
    }) {
      var y = 0
      while ( {
        y < height
      }) {
        image.setRGB(x, y, if (matrix.get(x, y)) BLACK
        else WHITE)

        {
          y += 1; y - 1
        }
      }

      {
        x += 1; x - 1
      }
    }
    image
  }


  @throws[IOException]
  def writeToFile(matrix: BitMatrix, format: String, file: File): Unit = {
    val image = toBufferedImage(matrix)
    if (!ImageIO.write(image, format, file)) throw new IOException("Could not write an image of format " + format + " to " + file)
  }


  @throws[IOException]
  def writeToStream(matrix: BitMatrix, format: String, stream: OutputStream): Unit = {
    val image = toBufferedImage(matrix)
    if (!ImageIO.write(image, format, stream)) throw new IOException("Could not write an image of format " + format)
  }

}
