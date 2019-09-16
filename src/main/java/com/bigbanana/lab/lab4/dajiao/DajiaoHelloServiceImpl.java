package com.bigbanana.lab.lab4.dajiao;


import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.stereotype.Service;

@Service
public class DajiaoHelloServiceImpl implements SpringBootInActionHelloService{


	@Override
	public String sayHello() {
		return "我是大蕉，我完成了lab4挑战";
	}
}
