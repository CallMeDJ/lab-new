package com.bigbanana.lab.lab4.dajiao2;


import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.stereotype.Service;

@Service
public class Dajiao2HelloServiceImpl implements SpringBootInActionHelloService{


	@Override
	public String sayHello() {
		return "我是大蕉2号，我完成了lab4挑战<br/>  <ul><li>我就是这么皮你来打我啊</li></ul>";
	}
}
