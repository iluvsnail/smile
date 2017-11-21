package com.smile.love.druid

import org.springframework.boot.context.properties.ConfigurationProperties

/**
  * User: chenyl 
  * Date: 2017-07-26  11:40 
  */
@ConfigurationProperties(prefix = "druid")
class DruidProperties {
  private var url = ""
  private var username = ""
  private var password = ""
  private var driverClass = ""

  private var maxActive = 0
  private var minIdle = 0
  private var initialSize = 0
  private var testOnBorrow = false

  def getUrl: String = url

  def setUrl(url: String): Unit = {
    this.url = url
  }

  def getUsername: String = username

  def setUsername(username: String): Unit = {
    this.username = username
  }

  def getPassword: String = password

  def setPassword(password: String): Unit = {
    this.password = password
  }

  def getDriverClass: String = driverClass

  def setDriverClass(driverClass: String): Unit = {
    this.driverClass = driverClass
  }

  def getMaxActive: Int = maxActive

  def setMaxActive(maxActive: Int): Unit = {
    this.maxActive = maxActive
  }

  def getMinIdle: Int = minIdle

  def setMinIdle(minIdle: Int): Unit = {
    this.minIdle = minIdle
  }

  def getInitialSize: Int = initialSize

  def setInitialSize(initialSize: Int): Unit = {
    this.initialSize = initialSize
  }

  def isTestOnBorrow: Boolean = testOnBorrow

  def setTestOnBorrow(testOnBorrow: Boolean): Unit = {
    this.testOnBorrow = testOnBorrow
  }

}
