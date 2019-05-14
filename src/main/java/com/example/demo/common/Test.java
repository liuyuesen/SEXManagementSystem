package com.example.demo.common;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResponseBody
public class Test {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test (){
        return "liuyuesen";
    }
}

//public class TestBean {
//
//}
