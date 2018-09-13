package com.smile.love.chat;

class MessageSender(val msg:String,val user:String,val count:Int) {
    def this()={
        this("","",1)
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
}