package com.oysookter.forestFire.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("테스트 hello API 호출됨");
        System.out.println("테스트 hello API 호출됨2222");
        return "Hello from Spring Boot!";
    }

}
