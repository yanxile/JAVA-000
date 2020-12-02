package com.homework;

import com.homework.sat02.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class W07ApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void testHomeWorkSat02() {
        testService.getUserFromMaster();
        testService.getUserFromSlave();
        testService.getUserFromSlave();
        testService.getUserFromSlave();
        testService.getUserFromSlave();
    }
}
