package com.smile.love.user

import com.smile.love.utils.UUIDGenerator

/**
  * User: chenyl 
  * Date: 2017-07-26  13:07 
  */
class User {
  private var id:String = null
  private var name:String  = null
  private var passwd:String  = null
  private var role:String  = null
  private var rtime:String  = null

  def getId: String = {
    if (id == null) id = UUIDGenerator.getDefaultUUID
    id
  }

  def setId(id: String): Unit = {
    this.id = id
  }

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getPasswd: String = passwd

  def setPasswd(passwd: String): Unit = {
    this.passwd = passwd
  }

  def getRole: String = role

  def setRole(role: String): Unit = {
    this.role = role
  }

  def getRegisterTime: String = rtime

  def setRegisterTime(registerTime: String): Unit = {
    this.rtime = registerTime
  }

}
