package com.smile.love.filter

import com.smile.love.user.User
import com.smile.love.utils.OnlineHelper
import javax.servlet.annotation.WebListener
import javax.servlet.http.{HttpSessionAttributeListener, HttpSessionBindingEvent}

@WebListener
class SessionAttributeListener extends HttpSessionAttributeListener {
     def attributeAdded(se: HttpSessionBindingEvent): Unit = {
       val ob=se.getSession.getAttribute("user")
        if(ob!=null){
            if(!OnlineHelper.isOnline(ob.asInstanceOf[User])){
                OnlineHelper.addUser(ob.asInstanceOf[User])
            }
        }
    }

     def attributeRemoved(se: HttpSessionBindingEvent): Unit = {

    }

     def attributeReplaced(se: HttpSessionBindingEvent): Unit = {

    }
}