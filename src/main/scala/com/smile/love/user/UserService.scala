package com.smile.love.user

import com.smile.love.common.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
  * User: chenyl 
  * Date: 2017-07-26  13:10 
  */
@Service
class UserService {
  @Autowired private val userMapper:UserMapper = null


  def login(user: User): Boolean = {
    val count = userMapper.login(user)
    if (count > 0) return true
    false
  }

  def findUserByName(name: String): User = userMapper.findUserByName(name)

  def insertLog(log: Log): Unit = {
    userMapper.insertLog(log)
  }

  def insert(qrCode: QRCode): Unit = {
    userMapper.insertQRCode(qrCode)
  }

  def getLatestQRCode: QRCode = {
    val page = new Page
    page.setOffset(0)
    page.setSize(1)
    userMapper.getLatestQRCode(page)
  }

}
