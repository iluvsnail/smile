package com.smile.love.user

import java.io.IOException
import javax.servlet.ServletOutputStream
import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import com.google.zxing.WriterException
import com.smile.love.utils._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import org.springframework.web.servlet.ModelAndView

/**
  * User: chenyl 
  * Date: 2017-07-26  13:08 
  */
@RestController
@RequestMapping(Array("/"))
class UserController {
  @Autowired private val userService:UserService = null

  @GetMapping(Array("/")) def home = new ModelAndView("redirect:/content/index")

  @RequestMapping(value = Array("/index"), method = Array(RequestMethod.GET)) def index = new ModelAndView("user/login")

  @PostMapping(Array("/login")) def login(session: HttpSession, request: HttpServletRequest, user: User): ModelAndView = {
    if (null == user.getName || null == user.getPasswd || "" == user.getPasswd || "" == user.getName) return new ModelAndView("redirect:/index")
    if (userService.login(user)) {
      val loginUser = userService.findUserByName(user.getName)
      session.setAttribute("user", loginUser)
      val log = new Log
      log.setUser(user.getName)
      var ip = NetworkUtil.getIpAddress(request)
      ip += "(" + URLUtil.getIpLocation(ip) + ")"
      log.setIp(ip)
      log.setLtime(TimeUtil.getNow)
      userService.insertLog(log)
      return new ModelAndView("redirect:/content/index")
    }
    new ModelAndView("redirect:/index")
  }

  @RequestMapping(value = Array("/tmp"), method = Array(RequestMethod.GET)) def getTemp(session: HttpSession, request: HttpServletRequest): ModelAndView = {
    request.setAttribute("dayCount", TimeUtil.getDayCount(2014, 9, 15))
    new ModelAndView("home")
  }

  @RequestMapping(value = Array("/getQRcode"), method = Array(RequestMethod.GET)) def getQRCode(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse): Unit = {
    var out:ServletOutputStream = null
    try {
      out = response.getOutputStream
      val qrCode = userService.getLatestQRCode
      QRCodeUtil.getQRcode(qrCode.getContent, out)
    } catch {
      case e: IOException =>
        e.printStackTrace()
      case e: WriterException =>
        e.printStackTrace()
    } finally try {
      out.flush()
      out.close()
    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  @RequestMapping(value = Array("/insertQRCode"), method = Array(RequestMethod.GET))
  def insertQRCode(qrCode: QRCode): QRCode = {
    qrCode.setId(UUIDGenerator.getDefaultUUID)
    qrCode.setTime(TimeUtil.getNow)
    userService.insert(qrCode)
    qrCode
  }

  @RequestMapping(value = Array("/weather/{cityCode}"), produces = Array("text/javascript;charset=UTF-8"))
  def getWeather(@PathVariable cityCode: String): String = URLUtil.getReturnString(URLUtil.generateURL(cityCode))

}
