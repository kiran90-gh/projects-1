package com.idream.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/idream1")
    public String serviceA() {
        return "Hello from Development!";
    }

    @GetMapping("/idream2")
    public String serviceB() {
        return "Hello from Testing!";
    }

    @GetMapping("/idream3")
    public String serviceC() {
        return "Hello from Devops!";
    }
}