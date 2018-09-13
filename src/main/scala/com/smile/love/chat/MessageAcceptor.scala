package com.smile.love.chat

class MessageAcceptor extends Serializable{

    var msg: String = ""
    var user:String = ""

    def  getMsg():String = {
         msg
    }
    def getUser():String = {
        user
    }

}