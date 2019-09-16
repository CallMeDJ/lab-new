package com.bigbanana.lab;

import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author 大蕉
 */
@RestController
public class WeAreTogetherController {

    @Resource
    private Set<SpringBootInActionHelloService> springBootInActionHelloServices;

    @RequestMapping(value = "/we")
    public String index(){
        StringBuffer result = new StringBuffer();

        for(SpringBootInActionHelloService springBootInActionHelloService:springBootInActionHelloServices){
            result.append("<br/>");
            result.append(springBootInActionHelloService.sayHello());
        }



        return result.toString();
    }
}
