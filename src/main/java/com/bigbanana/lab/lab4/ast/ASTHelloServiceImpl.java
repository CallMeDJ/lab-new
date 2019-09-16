package com.bigbanana.lab.lab4.ast;

import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.stereotype.Service;

/**
 * @Author Abe
 * @Date 2018/7/21.
 */
@Service
public class ASTHelloServiceImpl implements SpringBootInActionHelloService {
    @Override
    public String sayHello() {
        return "<div align=\"center\">\n" +
                "    <p>大家好, 我是小蕉</p>\n" +
                "    <img src=\"/images/dajiao.jpg\" alt=\"ast\"  />\n" +
                "</div>";
    }
}
