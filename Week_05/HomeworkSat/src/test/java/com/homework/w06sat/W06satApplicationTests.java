package com.homework.w06sat;

import com.homework.w06sat.homework04.School;
import com.homework.w06sat.homework04.Student;
import com.homework.w06sat.homework06.HikariDao;
import com.homework.w06sat.homework06.OriginalJdbc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class W06satApplicationTests {

    @Autowired
    private School school;
    @Autowired
    private HikariDao hikariDao;

    @Test
    public void homework04() {
        System.out.println("school.toString() = " + school.toString());
    }

    @Test
    public void homework06Jdbc() {
        OriginalJdbc.createTable();
        OriginalJdbc.createStudent(school.getStudent());
        OriginalJdbc.createStudentBatch(school.getClass1().getStudents());
        System.out.println("students = " + OriginalJdbc.reviewStudent());
        OriginalJdbc.updateStudent(new Student(3, "小天"));
        OriginalJdbc.deleteStudent(100);
        System.out.println("students = " + OriginalJdbc.reviewStudent());
        OriginalJdbc.dropTable();
    }

    @Test
    public void homework06Hikari() {
        hikariDao.createTable();
        hikariDao.createStudent(school.getStudent());
        OriginalJdbc.createStudentBatch(school.getClass1().getStudents());
        System.out.println("students = " + OriginalJdbc.reviewStudent());
        hikariDao.updateStudent(new Student(3, "小天"));
        hikariDao.deleteStudent(100);
        System.out.println("students = " + OriginalJdbc.reviewStudent());
        hikariDao.dropTable();
    }

}
