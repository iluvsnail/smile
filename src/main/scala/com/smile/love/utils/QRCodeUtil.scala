package com.smile.love.utils

import java.io.{IOException, OutputStream}

import com.google.zxing.{BarcodeFormat, EncodeHintType, MultiFormatWriter, WriterException}


/**
  * User: chenyl 
  * Date: 2017-07-26  12:45 
  */
object QRCodeUtil {
  @throws[IOException]
  @throws[WriterException]
  def getQRcode(text: String, ostrean: OutputStream): OutputStream = {
    val width = 500
    val height = 500
    val format = "png"
    val hints:java.util.Map[EncodeHintType,String] = new java.util.Hashtable[EncodeHintType, String]
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8")
    val bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints)
    MatrixToImageWriter.writeToStream(bitMatrix, format, ostrean)

    ostrean
  }
}
