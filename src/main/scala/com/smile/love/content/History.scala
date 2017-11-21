package com.smile.love.content

import com.smile.love.utils.UUIDGenerator

/**
  * User: chenyl 
  * Date: 2017-07-26  11:17 
  */
class History {
  private var id:String = null
  private var title:String = null
  private var content:String = null
  private var octime:String = null
  private var user:String = null
  private var contentid:String = null
  private var uptime:String = null

  def getUser: String = user

  def setUser(user: String): Unit = {
    this.user = user
  }

  def getId: String = {
    if (id == null || "" == id.trim || "null" == id.trim) id = UUIDGenerator.getDefaultUUID
    id
  }

  def setId(id: String): Unit = {
    this.id = id
  }

  def getTitle: String = title

  def setTitle(title: String): Unit = {
    this.title = title
  }

  def getContent: String = content

  def setContent(content: String): Unit = {
    this.content = content
  }

  def getOctime: String = octime

  def setOctime(octime: String): Unit = {
    this.octime = octime
  }

  def getContentid: String = contentid

  def setContentid(contentid: String): Unit = {
    this.contentid = contentid
  }

  def getUptime: String = uptime

  def setUptime(uptime: String): Unit = {
    this.uptime = uptime
  }

}
