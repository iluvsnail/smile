package com.smile.love.chat

import com.smile.love.utils.TimeUtil
;

class MessageSender(val msg:String,val user:String,val count:Int,val ou:String,val rtime:String) {
    def this()={
        this("","",1,"",TimeUtil.getNow)
    }

    def getMsg():String = {
        msg
    }
    def getUser():String = {
        user
    }
    def getCount():Int ={
        count
    }
    def getOu():String = {
        ou
    }
    def getRtime():String = {
        rtime
    }
}