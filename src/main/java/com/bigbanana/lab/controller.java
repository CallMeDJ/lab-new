package com.bigbanana.lab;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @RequestMapping(value = "/")
    public String index(){
        return "Hello World3";
    }
}
