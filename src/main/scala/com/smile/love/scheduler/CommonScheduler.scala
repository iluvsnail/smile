package com.smile.love.scheduler

import cn.hutool.extra.mail.{MailAccount, MailUtil}
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import redis.clients.jedis.{Jedis, JedisPool}

import scala.collection.JavaConversions._

@Service
class CommonScheduler {


    @Autowired
    private val restTemplate:RestTemplate = null
    @Autowired
    private val pool: JedisPool = null

    private val SPORTS= "sports"

    @Autowired
    private val mailAccount:MailAccount = null


    /**
      * TODO 没想好怎么做
      * @return
      */
    //@Scheduled(cron = "0 * * * * ? ")
    def getSports():Unit = {
        val document=Jsoup.connect("https://www.zhibo8.cc/").ignoreContentType(true).get();
        val redis = getRedisInstance()
        document.select(".schedule_container > .box").foreach(box =>{
            val key = SPORTS+":"+box.select("h2").attr("title")
            box.select("li").foreach(li=>{
                li.select("a").remove()
                val txt = li.text()
                val time = txt.substring(0,txt.indexOf(" "))
                val legateam = txt.substring(txt.indexOf(" "))
                val lega = legateam.substring(0,legateam.indexOf(" "))
                val team = legateam.substring(legateam.indexOf(" "))
               if(!redis.hexists(key,legateam)){
                    redis.hset(key,legateam,time)
                }
            })
        })
       redis.close()
    }

    private def getRedisInstance():Jedis = {
        var jedis:Jedis = null
        try jedis = pool.getResource
        catch {
            case e: Exception =>{
                if (jedis != null) jedis.close()
            }
        }
        jedis
    }
    @Scheduled(cron = "0/20 * * * * ? ")
    def sendMail():Unit = {
        println("start")
        MailUtil.send(mailAccount,"cyl@gfire.cn","test from hutools","zhishiceshieryi",false)
    }

}

object CommonScheduler {
    def main(args: Array[String]): Unit = {
        new CommonScheduler().getSports()
    }
}