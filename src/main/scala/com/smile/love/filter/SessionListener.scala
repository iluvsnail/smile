package com.smile.love.filter

import com.smile.love.user.User
import com.smile.love.utils.OnlineHelper
import javax.servlet.annotation.WebListener
import javax.servlet.http.{HttpSessionEvent, HttpSessionListener}

@WebListener
class SessionListener extends HttpSessionListener {
    override def sessionCreated(se: HttpSessionEvent): Unit = {

    }

    override def sessionDestroyed(se: HttpSessionEvent): Unit = {
        val ob=se.getSession.getAttribute("user")
        if(ob!=null){
            if(!OnlineHelper.isOnline(ob.asInstanceOf[User])){
                OnlineHelper.removeUser(ob.asInstanceOf[User])
            }
        }

    }
}