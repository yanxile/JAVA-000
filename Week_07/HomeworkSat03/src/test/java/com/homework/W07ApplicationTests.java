package com.homework;

import com.homework.sat03.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class W07ApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void testHomeWorkSat03() {
        testService.getUserFromSlave();
        testService.getUserFromSlave();
        testService.getUserFromSlave();
        testService.getUserFromSlave();
    }

    @Test
    public void testInsert() {
        testService.insertUser();
        testService.getUserFromMaster();
        testService.deleteUser();
        testService.getUserFromMaster();
    }
}
