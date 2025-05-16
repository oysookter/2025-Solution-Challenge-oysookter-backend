package com.oysookter.forestFire.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        log.info("test hello API call");
        return "Hello from Spring Boot!";
    }

}
