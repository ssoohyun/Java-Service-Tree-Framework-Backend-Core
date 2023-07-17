package com.arms.ssoohyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredTest {
//    @Autowired
//    private Start start;
//
//    public AutowiredTest() {
//        Start start = new Start();
//        start.test();
//    }

    private Start start;

    @Autowired
    public AutowiredTest(Start start) {
        this.start = start;
        start.test();
    }
}
