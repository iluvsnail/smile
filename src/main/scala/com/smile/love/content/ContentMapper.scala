package com.smile.love.content

import java.util

import com.smile.love.common.Page
import org.apache.ibatis.annotations.{Mapper, Select, Update}
import org.springframework.stereotype.Component

/**
  * User: chenyl 
  * Date: 2017-07-26  11:34 
  */
@Mapper
@Component
trait ContentMapper {
  @Select(Array("select * from content order by octime desc"))
  def getAllContent: util.List[Content]

  @Update(Array("insert into content (id,title,octime,content,user,img) values(#{id},#{title},#{octime},#{content},#{user},#{img})"))
  def addContent(content: Content): Unit

  @Select(Array("select id,title,content,octime,user from content order by octime desc limit #{size} offset #{offset}"))
  def getContentByPage(page: Page): util.List[Content]

  @Update(Array("update content set title = #{title},content=#{content},octime=#{octime} where id = #{id}"))
  def updateContent(content: Content): Unit

  @Update(Array("insert into history(id,title,octime,content,user,contentid,uptime) values(#{id},#{title},#{octime},#{content},#{user},#{contentid},#{uptime})"))
  def addHistory(history: History): Unit

  @Select(Array("select * from content where id =#{id}"))
  def getContent(content: Content): Content

  @Update(Array("update content set img = #{img} where id = #{id}"))
  def updateContentImage(content: Content): Unit

  @Select(Array("select count(*) from content"))
  def getAllContentCount: Int

}
