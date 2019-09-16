package com.bigbanana.lab.lab4.MrC;

import com.bigbanana.lab.lab4.SpringBootInActionHelloService;
import org.springframework.stereotype.Service;

@Service
public class MrCSayHelloServiceImpl implements SpringBootInActionHelloService {

    @Override
    public String sayHello() { 
        return  "\n<style>\n" +
                "body{\n" +
                "    background-color: #ccc;\n" +
                "}\n"+
                "    html, body {\n" +
                "      width: 100%;\n" +
                "      height: 100%;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    #container {\n" +
                "      overflow: hidden;\n" +
                "      position: absolute;\n" +
                "      top: 0; left: 47%; right: 0; bottom: 0;\n" +
                "    }\n" +
                "    #container {\n" +
                "      margin-left:auto;\n" +
                "      margin-right:auto;" +
                "      height: 100%;\n" +
                "    }\n" +
                "\n" +
                "    #main_content {\n" +
                "      z-index: 2;\n" +
                "      position: relative;\n" +
                "      display: inline-block;\n" +
                "\n" +
                "      /* Vertical center */\n" +
                "      top: 50%;\n" +
                "      transform: translateY(-50%);\n" +
                "    }\n" +
                "\n" +
                "    #main_content h1 {\n" +
                "      text-align: center;\n" +
                "      font-family: 'proxima-nova', Helvetica;\n" +
                "      text-transform: uppercase;\n" +
                "      font-weight: 600;\n" +
                "      font-family: 'proxima-nova-condensed', Helvetica;\n" +
                "      color: #fff;\n" +
                "      font-size: 35px;\n"+
                "      text-shadow:0 0 0.2em #f87,\n" +
                "                 -0 -0 0.2em #f87;\n" +
                "    }\n" +
                "\n" +
                "    #main_content .sub_head {\n" +
                "      text-align: center;\n" +
                "      font-family: 'proxima-nova', Helvetica;\n" +
                "      color: rgba(255,255,255,0.5);\n" +
                "      font-size: 18px;\n" +
                "      text-shadow:0 0 0.2em #f87,\n" +
                "                 -0 -0 0.2em #f87;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "  <div id=\"container\">\n" +
                "    <section id=\"main_content\">\n" +
                "      <div id=\"head\">\n" +
                "        <h1>Hello! I'm Mr.C</h1>\n" +
                "        <p class=\"sub_head\">Welcome to Banana's Family</p>\n" +
                "      </div>\n" +
                "    </section>\n" +
                "  </div>\n";
    }
}
