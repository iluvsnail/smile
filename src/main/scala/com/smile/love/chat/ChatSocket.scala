package com.smile.love.chat

import javax.websocket.server.ServerEndpoint

import org.springframework.stereotype.Component
import javax.websocket.{OnClose, OnMessage, OnOpen, Session}
import java.io.IOException
@ServerEndpoint(value = "/wso")
@Component
class ChatSocket {

  private var session:Session = _
  @OnOpen
  def onOpen(session: Session): Unit = {
    this.session = session

    try
      println("aaa")
    catch {
      case e: IOException =>
        System.out.println("IO异常")
    }
  }

  /**
    * 连接关闭调用的方法
    */
  @OnClose
  def onClose(): Unit = {

  }


  @OnMessage
  def onMessage(message: String, session: Session): Unit = {

  }
}
