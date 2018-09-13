package com.smile.love.chat

import com.smile.love.utils.OnlineHelper
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.{MessageMapping, SendTo}
import org.springframework.stereotype.Controller

/**
  * Author chenyl
  * Date 20170821
  */
@Controller
class SocketController {
    private val logger = LoggerFactory.getLogger(classOf[SocketController])

    @MessageMapping(value = Array("/message/test"))
    @SendTo(value = Array("/topic/response"))
    def say(acceptor: MessageAcceptor): MessageSender =  {
        new MessageSender(acceptor.getMsg,acceptor.getUser,OnlineHelper.count(),OnlineHelper.getOnlineUsers())
    }

}
