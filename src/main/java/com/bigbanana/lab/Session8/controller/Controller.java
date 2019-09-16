package com.bigbanana.lab.Session8.controller;

import com.bigbanana.lab.Session8.request.AddCartRequest;
import com.bigbanana.lab.Session8.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author 大蕉
 */
@RestController
public class Controller {

    @Resource
    private CartService cartService;

    @RequestMapping(value = "/")
    public String index(){

        AddCartRequest addCartRequest = new AddCartRequest();

        addCartRequest.setCount(1);
        addCartRequest.setCount(1);
        cartService.addCart(addCartRequest);

        return "hello";
    }
}
