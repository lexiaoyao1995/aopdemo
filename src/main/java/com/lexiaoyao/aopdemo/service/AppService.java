package com.lexiaoyao.aopdemo.service;

import com.lexiaoyao.aopdemo.Ann;
import com.lexiaoyao.aopdemo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppService {

    public Person function1(int n, String str) {
        log.info("service 的方法");
        return new Person("213", 12);
//        throw new RuntimeException("service方法的异常");
    }

    @Ann("ann value")
    public Person method2() {
        System.out.println("method2");
        return new Person("asd", 12);
    }

}
