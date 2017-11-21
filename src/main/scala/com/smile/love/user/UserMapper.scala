package com.smile.love.user

import com.smile.love.common.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.springframework.stereotype.Component


/**
  * User: chenyl 
  * Date: 2017-07-26  13:12 
  */
@Mapper
@Component
trait UserMapper {
  @Select(Array("select * from user where name = #{name}"))
  def findUserByName(name: String): User

  @Select(Array("select count(*) from user where name = #{name} and passwd = #{passwd}"))
  def login(user: User): Int

  @Update(Array("insert into log (id,user,ip,ltime) values(#{id},#{user},#{ip},#{ltime})"))
  def insertLog(log: Log): Unit

  @Update(Array("insert into QRCode (id,content,time) values(#{id},#{content},#{time})"))
  def insertQRCode(qrCode: QRCode): Unit

  @Select(Array("select * from QRCode order by time desc limit #{size} offset #{offset}"))
  def getLatestQRCode(page: Page): QRCode
}
