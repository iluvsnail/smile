package com.smile.love.utils

import org.slf4j.LoggerFactory
import javax.servlet.http.HttpServletRequest
import java.io.IOException


/**
  * User: chenyl 
  * Date: 2017-07-26  12:44 
  */
class NetworkUtil{

}
object NetworkUtil {
  /**
    * Logger for this class
    */
  private val log = LoggerFactory.getLogger(classOf[NetworkUtil])

  /**
    * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
    *
    * @param request
    * @return
    * @throws IOException
    */
  def getIpAddress(request: HttpServletRequest): String = { // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
    var ip = request.getHeader("X-Forwarded-For")
    if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip)) {
      if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("Proxy-Client-IP")
      if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("WL-Proxy-Client-IP")
      if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("HTTP_CLIENT_IP")
      if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("HTTP_X_FORWARDED_FOR")
      if (ip == null || ip.length == 0 || "unknown".equalsIgnoreCase(ip)) ip = request.getRemoteAddr
    }
    else if (ip.length > 15) {
      val ips = ip.split(",")
      var index = 0
      while ( {
        index < ips.length
      }) {
        val strIp = ips(index).asInstanceOf[String]
        if (!"unknown".equalsIgnoreCase(strIp)) {
          ip = strIp
        }

        {
          index += 1; index - 1
        }
      }
    }
    ip
  }

}
