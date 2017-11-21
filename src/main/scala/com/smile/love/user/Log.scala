package com.smile.love.user

import com.smile.love.utils.UUIDGenerator

/**
  * User: chenyl 
  * Date: 2017-07-26  13:05 
  */
class Log {
  private var id:String = null
  private var ip:String = null
  private var user:String = null
  private var ltime:String = null

  def getId: String = {
    if (id == null) id = UUIDGenerator.getDefaultUUID
    id
  }

  def setId(id: String): Unit = {
    this.id = id
  }

  def getIp: String = ip

  def setIp(ip: String): Unit = {
    this.ip = ip
  }

  def getUser: String = user

  def setUser(user: String): Unit = {
    this.user = user
  }

  def getLtime: String = ltime

  def setLtime(ltime: String): Unit = {
    this.ltime = ltime
  }

}
