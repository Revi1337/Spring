package com.example.aop2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class Sp5Chap0701Application {

    public static void main(String[] args) {
        SpringApplication.run(Sp5Chap0701Application.class, args);

        String base64Email = Base64.getEncoder().encodeToString("revi1337@naver.com".getBytes());
        System.out.println(base64Email);
    }

}
