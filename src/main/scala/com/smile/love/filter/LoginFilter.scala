package com.smile.love.filter

import javax.servlet._
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

import org.springframework.core.annotation.Order


/**
  * User: chenyl 
  * Date: 2017-07-26  12:37 
  */
@Order(1)
@WebFilter(filterName = "authFilter", urlPatterns = Array("/*"))
class LoginFilter extends Filter{

  import java.io.IOException
  import javax.servlet.{FilterConfig, ServletException, ServletRequest, ServletResponse}
  import javax.servlet.http.HttpServletResponse

  import org.slf4j.LoggerFactory

  private var config:FilterConfig = null

  private val log = LoggerFactory.getLogger(classOf[LoginFilter])

  def destroy(): Unit = {
    // TODO Auto-generated method stub
  }

  @throws[IOException]
  @throws[ServletException]
  def doFilter(arg0: ServletRequest, arg1: ServletResponse, arg2: FilterChain): Unit = {
    val request = arg0.asInstanceOf[HttpServletRequest]
    val session = request.getSession(true)
    val path = request.getServletPath
    val allexcludes = Array("/login","/register","/index","/insertQRCode")
    if (path == null || path == "" || path == "/" || allexcludes.contains(path)) {
      arg2.doFilter(arg0, arg1)
      return
    }
    if (path.endsWith(".js") || path.endsWith(".css") || path.endsWith(".png")) {
      arg2.doFilter(arg0, arg1)
      return
    }
    if (session.getAttribute("user") == null) {
      arg1.asInstanceOf[HttpServletResponse].sendRedirect("/index")
      return
    }
    arg2.doFilter(arg0, arg1)
  }


  @throws[ServletException]
  def init(arg0: FilterConfig): Unit = {
    this.config = arg0
  }
}
