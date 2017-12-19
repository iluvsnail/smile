package com.smile.love.utils

import java.text.{ParseException, SimpleDateFormat}
import java.util.{Calendar, Date}

/**
  * User: chenyl 
  * Date: 2017-07-26  12:53 
  */
object TimeUtil {
  private val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  @throws[ParseException]
  def main(args: Array[String]): Unit = {
    System.out.println(getFormatTime("2015-02-04 20:12:51"))

  }

  def getDayCount(date:String): String = {
    val sdf = new SimpleDateFormat("yyyyMMdd")
    val know = sdf.parse(date)
    val knowTime = know.getTime
    val now = System.currentTimeMillis

    val day = (now - knowTime) / 1000 / 60 / 60 / 24
    String.valueOf(day)
  }


  @throws[ParseException]
  def getFormatTime(time: String): String = {
    val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = df.parse(time)
    val c = Calendar.getInstance
    c.setTime(date)
    getFormatMonth(String.valueOf(c.get(Calendar.MONTH))) + " " + c.get(Calendar.DATE) + " ," + c.get(Calendar.YEAR)
  }

  private def getFormatMonth(month: String) = {
    /*switch(month){
       case "0" : month = "Janary";break;
       case "1" : month = "February";break;
       case "2" : month = "March";break;
       case "3" : month = "April";break;
       case "4" : month = "May";break;
       case "5" : month = "June";break;
       case "6" : month = "July";break;
       case "7" : month = "August";break;
       case "8" : month = "September";break;
       case "9" : month = "October";break;
       case "10" : month = "Novenber";break;
       case "11" : month = "December";break;
       }*/ month
  }

  def getNow: String = sdf.format(new Date)

}
