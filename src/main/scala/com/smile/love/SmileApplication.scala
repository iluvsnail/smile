package com.smile.love


import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan

/**
  * User: chenyl 
  * Date: 2017-07-26  14:19 
  */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = Array("com.smile.love"))
class SmileApplication{

}
object SmileApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SmileApplication])
  }
}
