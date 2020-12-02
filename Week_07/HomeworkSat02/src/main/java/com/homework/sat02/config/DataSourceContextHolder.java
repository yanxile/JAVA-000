package com.homework.sat02.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {
    private static ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    public static void switchDataSource(String datasource) {
        log.debug("switchDataSource: {}", datasource);
        datasourceContext.set(datasource);
    }

    public static String getDataSource() {
        return datasourceContext.get();
    }

    public static void clear() {
        datasourceContext.remove();
    }
}
