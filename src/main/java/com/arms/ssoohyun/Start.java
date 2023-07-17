package com.arms.ssoohyun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Start { // 클래스 == 타입: 다른 사람이 사용할 수 있도록 필드, 메소드 잘 만들어야 함

    // 제한자: public, protected, private

    // 필드
    // public String test = 1;
    // public AutowiredTest at = 1;
    public Integer integer = 1;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 생성자: 첫 글자 대문자
    public Start() {
        logger.info("-----------------------------------------------------------------------");
    }

    // 메소드
    public void test() {
        logger.info("---------------------------------------------test---------------------------------------------");
    }
}
