package com.smile.love.chat

import com.smile.love.common.Page
import org.apache.ibatis.annotations.{Mapper, Select, Update}
import org.springframework.stereotype.Component


/**
  * User: chenyl 
  * Date: 2017-07-26  13:12 
  */
@Mapper
@Component
trait ChatHistoryMapper {

  @Update(Array("insert into chat_history (id,user,message,rtime) values(#{id},#{user},#{message},#{rtime})"))
  def insertChatHistory(chatHistory: ChatHistory): Unit

  @Select(Array("select * from chat_history order by rtime desc limit #{size} offset #{offset}"))
  def getChatHistory(page: Page):java.util.List[ChatHistory]
}
