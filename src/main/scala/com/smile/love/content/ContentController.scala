package com.smile.love.content

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, IOException}
import java.text.{ParseException, SimpleDateFormat}
import java.util
import java.util._
import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import com.smile.love.user.User
import com.smile.love.utils.TimeUtil
import net.coobird.thumbnailator.Thumbnails
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView

/**
  * User: chenyl 
  * Date: 2017-07-26  11:16 
  */
@RestController
@RequestMapping(value = Array("/content"))
class ContentController {
  private val logger = LoggerFactory.getLogger(classOf[ContentController])

  @Autowired private val contentService:ContentService = null

  @RequestMapping(value = Array("/getAllContent")) def getAllContent(request: HttpServletRequest): util.List[Content] = {
    val contentList = contentService.getAllContent
    contentList
  }

  @RequestMapping(value = Array("/getContentByPage")) def getContentByPage(request: HttpServletRequest, page: Int): util.List[Content] = {
    val contentList = contentService.getContentByPage(page)
    contentList
  }

  @RequestMapping(value = Array("/index")) def index(request: HttpServletRequest): ModelAndView = {
    request.setAttribute("dayCount", TimeUtil.getDayCount(2014, 9, 15))
    request.setAttribute("dayCount2", TimeUtil.getDayCount(2015, 4, 18))
    request.setAttribute("allCount", contentService.getAllContentCount)
    val df = new SimpleDateFormat("yyyy年MM月dd日")
    request.setAttribute("date", df.format(new Date))
    new ModelAndView("content/index")
  }

  @RequestMapping(value = Array("/addContent"), method = Array(RequestMethod.POST)) def addContent(request: HttpServletRequest, content: Content, session: HttpSession, id: String): util.Map[String, String] = {
    val retMap = new util.HashMap[String, String]
    var user:User = null
    if (session.getAttribute("user") != null) {
      user = session.getAttribute("user").asInstanceOf[User]
      content.setUser(user.getId)
    }
    else {
      retMap.put("result", "error")
      return retMap
    }
    try {
      if (null == id || "" == id.trim || "null" == id.trim) {
        contentService.addContent(content)
        retMap.put("contentId", content.getId)
      }
      else {
        retMap.put("update", "update")
        contentService.updateContent(content, user.getId)
      }
      retMap.put("result", "success")
    } catch {
      case e: ParseException =>
        retMap.put("result", "error")
    }
    retMap
  }

  @RequestMapping(Array("/uploadImage")) def upload(@RequestParam("file-zh") file: MultipartFile, session: HttpSession, request: HttpServletRequest, response: HttpServletResponse, id: String): util.Map[String, String] = {
    val retMap = new util.HashMap[String, String]
    var user:User = null
    val content = new Content
    if (session.getAttribute("user") != null) {
      user = session.getAttribute("user").asInstanceOf[User]
      content.setUser(user.getId)
    }
    else {
      retMap.put("result", "error")
      return retMap
    }
    try {
      content.setImg(file.getBytes)
      if (id != null && !id.isEmpty) {
        content.setId(id)
        contentService.updateContentImage(content)
      }
      else contentService.addContent(content)
      retMap.put("id", content.getId)
      retMap.put("result", "success")
    } catch {
      case e: IOException =>
        e.printStackTrace()
      case e: ParseException =>
        e.printStackTrace()
    }
    retMap
  }

  @RequestMapping(value = Array("/img/{contentid}"), produces = Array("text/javascript;charset=UTF-8"))
  def getImage(@PathVariable contentid: String): ResponseEntity[_] = {
    val content:Content = contentService.getContent(contentid)
    try
        if (content.getImg != null && !(content.getImg.length < 1)) {
          var img = Array.emptyByteArray
          if (content.getImg.length > 524288) {
            val bis = new ByteArrayInputStream(content.getImg)
            val os:ByteArrayOutputStream = new ByteArrayOutputStream
            Thumbnails.of(bis).scale(0.2f).toOutputStream(os)
            img = os.toByteArray
          }
          else img = content.getImg
          ResponseEntity.ok(img)
        }
        else {
          val resource = new ClassPathResource("static/img/" + (new Random().nextInt(30) + 1) + ".jpg")
          val img = new Array[Byte](resource.getInputStream.available)
          resource.getInputStream.read(img)
          ResponseEntity.ok(img)
        }
    catch {
      case e: Exception =>
        e.printStackTrace()
        null
    }
  }

}
