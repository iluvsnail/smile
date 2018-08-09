package com.smile.love.config;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Config {

    @Bean
    def  restTemplate():RestTemplate = {
        new RestTemplate()
    }
}