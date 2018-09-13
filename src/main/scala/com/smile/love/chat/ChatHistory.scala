package com.smile.love.chat

import com.smile.love.utils.UUIDGenerator

/**
 *
 * creat_user: chenyl
 * creat_date: 2018-09-13 16:14:05
 **/
class ChatHistory {
  private var id:String = null
  private var message:String  = null
  private var rtime:String  = null
  private var user:String = null

  def getId: String = {
    if (id == null) id = UUIDGenerator.getDefaultUUID
    id
  }

  def setId(id: String): Unit = {
    this.id = id
  }

  def getRtime: String = rtime

  def setRtime(rtime: String): Unit = {
    this.rtime = rtime
  }

  def setUser(user:String) = {
    this.user = user
  }

  def getUser():String = {
    this.user
  }

  def setMessage(msg:String)={
    this.message = msg
  }
  def getMessage():String={
    this.message
  }
}
