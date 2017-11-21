package com.smile.love.druid

import java.sql.SQLException
import javax.sql.DataSource

import com.alibaba.druid.pool.DruidDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.{ConditionalOnClass, ConditionalOnProperty}
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.{Bean, Configuration}

/**
  * User: chenyl 
  * Date: 2017-07-26  11:38 
  */
@Configuration
@EnableConfigurationProperties(Array(classOf[DruidProperties]))
@ConditionalOnClass(Array(classOf[DruidDataSource]))
@ConditionalOnProperty(prefix = "druid", name = Array("url"))
@AutoConfigureBefore(Array(classOf[DataSourceAutoConfiguration]))
class DruidAutoConfiguration {
  @Autowired private val properties: DruidProperties = null

  @Bean def dataSource: DataSource = {
    val dataSource = new DruidDataSource
    dataSource.setUrl(properties.getUrl)
    dataSource.setUsername(properties.getUsername)
    dataSource.setPassword(properties.getPassword)
    if (properties.getInitialSize > 0) dataSource.setInitialSize(properties.getInitialSize)
    if (properties.getMinIdle > 0) dataSource.setMinIdle(properties.getMinIdle)
    if (properties.getMaxActive > 0) dataSource.setMaxActive(properties.getMaxActive)
    dataSource.setTestOnBorrow(properties.isTestOnBorrow)
    try
      dataSource.init()
    catch {
      case e: SQLException =>
        throw new RuntimeException(e)
    }
    dataSource
  }

}
