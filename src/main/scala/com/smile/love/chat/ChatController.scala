package com.smile.love.chat

import java.util

import com.smile.love.content.Content
import com.smile.love.utils.OnlineHelper
import javax.servlet.http.{HttpServletRequest, HttpSession}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import org.springframework.web.servlet.ModelAndView

/**
  * Author chenyl
  * Date 20170821
  */
@RestController
@RequestMapping(value = Array("/chat"))
class ChatController {
  private val logger = LoggerFactory.getLogger(classOf[ChatController])

  @Autowired
  private val chatService:ChatHistoryService = null


  @RequestMapping(value = Array("/index")) def index(request: HttpServletRequest,session:HttpSession): ModelAndView = {
    request.setAttribute("user", session.getAttribute("user"))
    request.setAttribute("count", OnlineHelper.count())
    request.setAttribute("online",OnlineHelper.getOnlineUsers())
    new ModelAndView("chat/index")
  }

  @RequestMapping(value = Array("/getChatHistory"))
  def getContentByPage(request: HttpServletRequest):util.List[ChatHistory] = {
    val chatHistoryList = chatService.getChatHistory
    chatHistoryList
  }
}
