package com.smile.love.chat

import com.smile.love.utils.{OnlineHelper, TimeUtil}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.{MessageMapping, SendTo}
import org.springframework.stereotype.Controller

/**
  * Author chenyl
  * Date 20170821
  */
@Controller
class SocketController {
    private val logger = LoggerFactory.getLogger(classOf[SocketController])

    @Autowired
    private val chatHistoryService:ChatHistoryService=null
    @MessageMapping(value = Array("/message/test"))
    @SendTo(value = Array("/topic/response"))
    def say(acceptor: MessageAcceptor): MessageSender =  {
        val chatHistory = new ChatHistory
        chatHistory.setUser(acceptor.getUser())
        chatHistory.setMessage(acceptor.getMsg())
        chatHistory.setRtime(TimeUtil.getNow)
        chatHistoryService.insertChatHistory(chatHistory)
        new MessageSender(acceptor.getMsg,acceptor.getUser,OnlineHelper.count(),OnlineHelper.getOnlineUsers(),chatHistory.getRtime)
    }

}
