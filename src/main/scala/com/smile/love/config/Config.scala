package com.smile.love.config;

import cn.hutool.extra.mail.{MailAccount, MailUtil}
import javax.annotation.PostConstruct
import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import redis.clients.jedis.JedisPool

@Configuration
class Config {
    @Value("${redis.host.ip}")
    val redisIP:String = null
    @Value("${redis.host.port}")
    val redisPort:Int = 0


    private var pool: JedisPool = null

    @Value("${mail.host}")
    val host:String = null
    @Value("${mail.port}")
    val port:Int = 0
    @Value("${mail.user}")
    val user:String = null
    @Value("${mail.pass}")
    val pass:String = null
    @Value("${mail.from}")
    val from:String = null
    @Value("${mail.ssl}")
    val ssl:Boolean = false

    @PostConstruct
    private def init(): Unit = {
        val config = new GenericObjectPoolConfig
        config.setMaxIdle(200)
        config.setMaxTotal(5120)
        config.setMaxWaitMillis(5000)
        config.setTestOnBorrow(true)
        config.setTestOnReturn(true)
        pool = new JedisPool(config, redisIP, redisPort,60000)
    }

    @Bean
    def  restTemplate():RestTemplate = {
        new RestTemplate()
    }

    @Bean
    def getRedisPool():JedisPool = {
        pool
    }

    @Bean
    def getMailAccount():MailAccount = {
        val ma = new MailAccount()
        ma.setHost(host)
        ma.setPort(port)
        ma.setFrom(from)
        ma.setUser(user)
        ma.setPass(pass)
        ma.setSslEnable(ssl)
    }

}