package com.homework.w06sat;

import com.homework.w06sat.homework04.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class W06satApplicationTests {

    @Autowired
    private School school;

    @Test
    public void homework04() {
        System.out.println("school.toString() = " + school.toString());
    }

}
