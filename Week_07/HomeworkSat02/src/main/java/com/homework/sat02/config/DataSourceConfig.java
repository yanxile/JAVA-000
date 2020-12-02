package com.homework.sat02.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {
    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slaveFirstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave2")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slaveSecondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", masterDataSource());
        dataSourceMap.put("slave1", slaveFirstDataSource());
        dataSourceMap.put("slave2", slaveSecondDataSource());
        //设置动态数据源
        MyDataSource myDataSource = new MyDataSource();
        myDataSource.setTargetDataSources(dataSourceMap);
        myDataSource.setDefaultTargetDataSource(masterDataSource());

        return myDataSource;
    }

}
