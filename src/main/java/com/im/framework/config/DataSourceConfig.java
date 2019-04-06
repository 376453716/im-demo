package com.im.framework.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author Xiong Hao
 */
@Configuration
public class DataSourceConfig {

    public Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.im")
    public DataSource imDatasource() {
        logger.info("=================>init datasource-im");
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    @DependsOn({"imDatasource"})
    public DataSource im() {
        return new Log4jdbcProxyDataSource(imDatasource());
    }


}
