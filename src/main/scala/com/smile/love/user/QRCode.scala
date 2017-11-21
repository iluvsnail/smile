package com.smile.love.user

/**
  * User: chenyl 
  * Date: 2017-07-26  13:06 
  */
class QRCode {
  private var id:String = null
  private var content:String  = null
  private var time:String  = null

  def getId: String = id

  def setId(id: String): Unit = {
    this.id = id
  }

  def getContent: String = if (content == null || "" == content.trim || "null" == content.trim) "咩咩，俺稀罕你！"
  else content

  def setContent(content: String): Unit = {
    this.content = content
  }

  def getTime: String = time

  def setTime(time: String): Unit = {
    this.time = time
  }

}
