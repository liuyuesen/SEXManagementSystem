package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller  // 添加了一个注解
public class SexManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SexManagementSystemApplication.class, args);
    }

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(){

//		model.addAttribute("name", name);
        return "what";
    }
}
