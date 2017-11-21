package com.smile.love.content

import java.text.{ParseException, SimpleDateFormat}
import java.util
import java.util.Date

import com.smile.love.common.Page
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * User: chenyl 
  * Date: 2017-07-26  11:32 
  */
@Service
class ContentService {
  private val logger = LoggerFactory.getLogger(classOf[ContentService])

  @Autowired
  private val contentMapper:ContentMapper = null


  def getAllContent: util.List[Content] = contentMapper.getAllContent


  @throws[ParseException]
  def addContent(content: Content): Unit = {
    if (content.getOctime != null) {
      val sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      sf.parse(content.getOctime)
    }
    contentMapper.addContent(content)
  }


  def getContentByPage(page: Int): util.List[Content] = {
    val p = new Page
    p.setSize(10)
    p.setOffset(10 * page)
    contentMapper.getContentByPage(p)
  }


  def updateContent(content: Content, userId: String): Unit = {
    val updateContent = contentMapper.getContent(content)
    val history = new History
    val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    history.setUptime(df.format(new Date))
    history.setContent(updateContent.getContent)
    history.setContentid(updateContent.getId)
    history.setOctime(updateContent.getOctime)
    history.setTitle(updateContent.getTitle)
    history.setUser(userId)
    contentMapper.addHistory(history)
    contentMapper.updateContent(content)
  }

  def updateContentImage(content: Content): Unit = {
    contentMapper.updateContentImage(content)
  }

  def getContent(contentid: String): Content = {
    val cnt = new Content
    cnt.setId(contentid)
    contentMapper.getContent(cnt)
  }

  def getAllContentCount: Int = contentMapper.getAllContentCount

}
