package com.smile.love.utils

import java.net.{URL, URLEncoder}
import java.security.InvalidKeyException
import java.text.SimpleDateFormat
import java.util.Date
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import scala.io.Source

/**
  * User: chenyl 
  * Date: 2017-07-26  12:55 
  */
object URLUtil {
  private val last2byte = Integer.parseInt("00000011", 2).toChar
  private val last4byte = Integer.parseInt("00001111", 2).toChar
  private val last6byte = Integer.parseInt("00111111", 2).toChar
  private val lead6byte = Integer.parseInt("11111100", 2).toChar
  private val lead4byte = Integer.parseInt("11110000", 2).toChar
  private val lead2byte = Integer.parseInt("11000000", 2).toChar
  private val encodeTable = Array[Char]('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/')

  def standardURLEncoder(data: String, key: String): String = {
    val df = new SimpleDateFormat("yyyyMMddHHmm")
    val date = df.format(new Date)
    val appid = "&appid=3d631127cb506a92&date=" + date
    val appid0 = "&appid=3d6311&date=" + date
    var byteHMAC = Array.emptyByteArray
    var urlEncoder = ""
    try {
      val mac = Mac.getInstance("HmacSHA1")
      val spec = new SecretKeySpec(key.getBytes, "HmacSHA1")
      mac.init(spec)
      byteHMAC = mac.doFinal((data + appid).getBytes)
      if (byteHMAC != null) {
        val oauth = encode(byteHMAC)
        if (oauth != null) urlEncoder = URLEncoder.encode(oauth, "utf8")
      }
    } catch {
      case e1: InvalidKeyException =>
        e1.printStackTrace()
      case e2: Exception =>
        e2.printStackTrace()
    }
    data + appid0 + "&key=" + urlEncoder
  }

  def encode(from: Array[Byte]): String = {
    val to = new StringBuffer((from.length * 1.34).toInt + 3)
    var num = 0
    var currentByte = 0
    var i = 0
    while ( {
      i < from.length
    }) {
      num = num % 8
      while ( {
        num < 8
      }) {
        num match {
          case 0 =>
            currentByte = (from(i) & lead6byte).toChar
            currentByte = (currentByte >>> 2).toChar
          case 2 =>
            currentByte = (from(i) & last6byte).toChar
          case 4 =>
            currentByte = (from(i) & last4byte).toChar
            currentByte = (currentByte << 2).toChar
            if ((i + 1) < from.length) currentByte |= (from(i + 1) & lead2byte) >>> 6
          case 6 =>
            currentByte = (from(i) & last2byte).toChar
            currentByte = (currentByte << 4).toChar
            if ((i + 1) < from.length) currentByte |= (from(i + 1) & lead4byte) >>> 4
        }
        to.append(encodeTable(currentByte))
        num += 6
      }

      {
        i += 1; i - 1
      }
    }
    if (to.length % 4 != 0) {
      var i = 4 - to.length % 4
      while ( {
        i > 0
      }) {
        to.append("=")

        {
          i -= 1; i + 1
        }
      }
    }
    to.toString
  }

  def generateURL(cityCode: String): String = {
    val url = "http://open.weather.com.cn/data/?type=forecast_v&areaid=" + cityCode
    val key = "77ec0d_SmartWeatherAPI_c84e3e3"
    standardURLEncoder(url, key)
  }

  def main(args: Array[String]): Unit = {
    try
      /*	//��Ҫ���ܵ�����
                 String data = "http://open.weather.com.cn/data/?areaid=101290308&date=201501122200&type=index_v";
                 //��Կ
                 String key = "77ec0d_SmartWeatherAPI_c84e3e3";

                 String str = standardURLEncoder(data, key);*//*System.out.println(generateURL("101290308"));*/ getIpLocation("111.37.16.244")
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  def getReturnString(URL: String): String = {
    val temp = new StringBuffer
    try {
      val url = new URL(URL)
      url.openConnection
      val is = url.openStream
      val source=Source.fromInputStream(is, "utf-8")
      source.getLines().foreach(line=>{
        temp.append(line)
      })
      source.close()
      is.close()
      return temp.toString
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    temp.toString
  }

  def getIpLocation(ip: String): String = {
    var result = getReturnString("http://wap.ip138.com/ip_search138.asp?ip=" + ip)
    if (!result.isEmpty) {
      result = result.substring(result.lastIndexOf(ip) + 13)
      result = result.substring(result.indexOf("<b>") + 3)
      result = result.substring(0, result.indexOf("</b>"))
      return result
    }
    ip
  }

}
