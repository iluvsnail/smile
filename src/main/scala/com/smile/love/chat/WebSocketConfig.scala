package com.smile.love.chat

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig extends WebSocketMessageBrokerConfigurer {


    @Override
    def registerStompEndpoints( stompEndpointRegistry:StompEndpointRegistry)= {
        stompEndpointRegistry.addEndpoint("/endpoint").withSockJS()//注册STOMP协议的节点，映射指定的URL，并指定使用SockJS协议
    }

    @Override
    def configureMessageBroker( registry:MessageBrokerRegistry)= {//配置消息代码（Message Broker）
        registry.enableSimpleBroker("/topic");//广播式应配置一个/topic消息代理
    }
}