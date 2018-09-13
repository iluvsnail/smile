package com.smile.love.chat

import com.smile.love.common.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
  * User: chenyl 
  * Date: 2017-07-26  13:10 
  */
@Service
class ChatHistoryService {
  @Autowired private val chatHistoryMapper:ChatHistoryMapper = null


  def insertChatHistory(chatHistory: ChatHistory): Unit = {
    chatHistoryMapper.insertChatHistory(chatHistory)
  }

  def getChatHistory: java.util.List[ChatHistory] = {
    val page = new Page
    page.setOffset(0)
    page.setSize(10)
    chatHistoryMapper.getChatHistory(page)
  }

}
