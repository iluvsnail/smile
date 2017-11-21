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

  def getDayCount(year: Int, month: Int, day: Int): String = {
    val calendar = Calendar.getInstance
    val know = getDays(year, month - 1, day)
    val result = getDays(calendar.get(1), calendar.get(2), calendar.get(5)) - know
    String.valueOf(result)
  }


  def getDays(year: Int, month: Int, day: Int): Long = {
    val yearDays = getYearDay(year)
    val monthDays = getMonthDay(year, month)
    val days = getDay(day)
    yearDays + monthDays + days
  }

  private def getDay(day: Int) = day - 1

  private def getMonthDay(year: Int, month: Int) = {
    var monthDays = 0L
    month match {
      case 11 =>
        monthDays += 30L
      case 10 =>
        monthDays += 31L
      case 9 =>
        monthDays += 30L
      case 8 =>
        monthDays += 31L
      case 7 =>
        monthDays += 31L
      case 6 =>
        monthDays += 30L
      case 5 =>
        monthDays += 31L
      case 4 =>
        monthDays += 30L
      case 3 =>
        monthDays += 31L
      case 2 =>
        monthDays += getFebDay(year)
      case 1 =>
        monthDays += 31L
    }
    monthDays
  }

  private def getFebDay(year: Int) = {
    if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
      29L
    }else{
      28L
    }
  }

  private def getYearDay(year: Int) = {
    val years = year - 2000
    val leaps = years / 4 + 1
    years * 365 + leaps
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
