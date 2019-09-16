package com.bigbanana.lab.lab4.dashan;


import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.stereotype.Service;

@Service
public class LihaoshanHelloServiceImpl implements SpringBootInActionHelloService {


	@Override
	public String sayHello() {
		return "<div style='border:1px solid orange; border-radius:25px; width:400px;'><h3 style='color:blue;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我是大山，我完成了lab4挑战&#9786</h3>" +
				"<audio controls=\"controls\" autoplay=\"autoplay\">\n" +
				"  <source src=\"http://www.ytmp3.cn/down/23310.mp3\">\n" +
				"<embed height=\"100\" width=\"100\" src=\"http://www.ytmp3.cn/down/23310.mp3\" />\n" +
				"</audio> </div>";
	}
}
