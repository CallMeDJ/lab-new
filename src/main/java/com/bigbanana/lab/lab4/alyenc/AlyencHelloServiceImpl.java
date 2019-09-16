package com.bigbanana.lab.lab4.alyenc;

import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.stereotype.Service;

/**
 * @description
 * @package com.bigbanana.lab.lab4.alyenc
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/7/21 22:58
 * @version v1.0.0
 */
@Service
public class AlyencHelloServiceImpl implements SpringBootInActionHelloService {

    @Override
    public String sayHello() {
        return  "<div align=\"center\">\n" +
                "    <img src=\"/images/djqrcode.png\" alt=\"ast\"  />\n" +
                "    <p>微信扫一扫，有惊喜</p>\n" +
                "</div>";
    }
}
