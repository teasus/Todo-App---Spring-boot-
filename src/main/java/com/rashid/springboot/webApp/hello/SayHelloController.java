package com.rashid.springboot.webApp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SayHelloController {
        @RequestMapping("/hello")
        @ResponseBody
        public String sayHello(){

            StringBuffer sb = new StringBuffer();
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<Title>");
            sb.append("APP");
            sb.append("</Title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("Hello, What are you learning today ?");
            sb.append("</body>");
            sb.append("</html>");
            return sb.toString();
        }
        //redirect hello-jsp to hello


        //src/main/resources/META-INF/resources/WEB-INF/jsp/sayHellojsp.jsp
        @RequestMapping("/sayHellojsp")
        public String sayHellojsp(){
            return "sayHellojsp";

        }

}
