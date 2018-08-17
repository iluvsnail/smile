package com.smile.love.scheduler

import java.util.Calendar

import com.smile.love.user.UserService
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import scala.collection.JavaConversions._

@Service
class HolidayScheduler {


    @Autowired
    private val restTemplate:RestTemplate = null

    @Autowired
    private val userService:UserService = null

    @Scheduled(cron = "0 0 0 * * ? ")
    def sayGoodHoliday()={
        val holiday=getHoliday()
        if(holiday!=null && !holiday.trim.isEmpty)
            restTemplate.postForObject("http://192.168.10.67:923/send/wechat/smile/"+holiday+"快乐！",null,classOf[String])
    }

    def main(args: Array[String]): Unit = {
       val h= getHoliday()
        if (h!=null && !h.trim.isEmpty) {
            println("not empty")
        }else{
            println("empty")
        }
    }
    @Scheduled(cron = "0 0 9 * * ? ")
    def sayGoodDay()={
        val holiday=getHoliday()
        println("say good to family excute!")
        if(holiday!=null && !holiday.trim.isEmpty)
            restTemplate.postForObject("http://192.168.10.67:923/send/wechat/family/"+holiday+"快乐！要记得对自己好点！",null,classOf[String])
        else{
            restTemplate.postForObject("http://192.168.10.67:923/send/wechat/smile/要开心，要快乐，要记得对自己好点！",null,classOf[String])
            restTemplate.postForObject("http://192.168.10.67:923/send/wechat/family/要开心，要快乐，要记得对自己好点！",null,classOf[String])
        }
    }

    @Scheduled(cron = "0 0 18 * * ?")
    def signHackpai()={
        restTemplate.postForObject("http://192.168.10.67:923/send/wechat/smile/宝宝，想吃拔丝蛋糕么？",null,classOf[String])
    }


    def getHoliday():String = {
        val document=Jsoup.connect("https://wannianrili.51240.com/").ignoreContentType(true).get();
        val day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var dayStr = String.valueOf(day)
        if(day<10){
            dayStr="0" + dayStr
        }
        var rst = ""
        document.select(".wnrl_k_you").foreach(div => {
            if(div.select(".wnrl_k_you_id_wnrl_riqi").text().equals(dayStr)){
               rst= div.select(".wnrl_k_you_id_wnrl_jieri_neirong").text()
            }
        })
        rst
    }
}
