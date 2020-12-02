package com.homework.sat02.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class MyDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
