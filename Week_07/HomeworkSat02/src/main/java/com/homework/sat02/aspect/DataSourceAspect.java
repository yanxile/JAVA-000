package com.homework.sat02.aspect;

import com.homework.sat02.annotation.ReadOnly;
import com.homework.sat02.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    static Integer i = 1;

    @Pointcut("@annotation(com.homework.sat02.annotation.ReadOnly)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeExecute(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ReadOnly annotation = method.getAnnotation(ReadOnly.class);
        if (null == annotation) {
            annotation = joinPoint.getTarget().getClass().getAnnotation(ReadOnly.class);
        }
        if (null != annotation) {
            // 切换数据源
            if ("master".equals(annotation.value())) {
                DataSourceContextHolder.switchDataSource(annotation.value());
            } else {
                DataSourceContextHolder.switchDataSource(annotation.value() + i);
                i = (i + 1) > 2 ? (i + 1) % 2 : i + 1;
            }
        }
    }

    @After("pointcut()")
    public void afterExecute() {
        DataSourceContextHolder.clear();
    }

}
