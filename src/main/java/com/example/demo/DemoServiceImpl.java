package com.example.demo;


import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(endpointInterface = "com.example.demo.DemoService",serviceName ="DemoService" ,targetNamespace = "http://chenjx.vip")
@Component
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHi(String text) {
        return "Hello " + text;
    }


}
