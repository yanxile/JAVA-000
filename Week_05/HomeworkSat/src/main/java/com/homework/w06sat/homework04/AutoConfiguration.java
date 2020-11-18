package com.homework.w06sat.homework04;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AutoConfiguration {

    @Bean
    Student student100() {
        return new Student(100, "小红");
    }

    @Bean
    List<Student> students() {
        List<Student> list = new ArrayList();
        list.add(new Student(1, "小王"));
        list.add(new Student(2, "小李"));
        list.add(new Student(3, "小明"));
        return list;
    }

    @Bean
    @ConditionalOnBean(name = "students")
    Klass class1(List<Student> students) {
        return new Klass(students);
    }

    @Bean
    @ConditionalOnBean(name = {"class1","student100"})
    School school(Klass class1, Student student100) {
        return new School(class1, student100);
    }
}
