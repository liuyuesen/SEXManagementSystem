package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller  // 添加了一个注解
public class SexManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SexManagementSystemApplication.class, args);
    }

    @RequestMapping("hello")
    public String hello(){

//		model.addAttribute("name", name);
        return "index";
    }
}
