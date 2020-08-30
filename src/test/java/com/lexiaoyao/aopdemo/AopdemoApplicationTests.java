package com.lexiaoyao.aopdemo;

import com.lexiaoyao.aopdemo.model.Person;
import com.lexiaoyao.aopdemo.service.AppService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopdemoApplicationTests {

    @Autowired
    private AppService service;

    @Test
    void contextLoads() {
        service.method2();
        service.function1(12, "asd");

    }

}
