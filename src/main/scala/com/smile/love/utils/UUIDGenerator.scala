package com.smile.love.utils

import java.util.UUID

/**
  * User: chenyl 
  * Date: 2017-07-26  12:57 
  */
object UUIDGenerator {
  /**
    * 返回 32位的UUID
    *
    * @return
    */
  def getDefaultUUID: String = UUID.randomUUID.toString.replace("-", "")
 }
