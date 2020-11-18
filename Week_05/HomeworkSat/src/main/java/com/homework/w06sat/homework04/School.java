package com.homework.w06sat.homework04;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@ToString
@AllArgsConstructor
public class School{
    Klass class1;
    Student student;
}
