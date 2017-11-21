package com.smile.love.chat

import javax.servlet.http.HttpServletRequest

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
  private val chatService:ChatService = null


  @RequestMapping(value = Array("/index")) def index(request: HttpServletRequest): ModelAndView = {
    new ModelAndView("chat/index")
  }


}
