package com.example.demo;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name="DemoService",targetNamespace = "http://chenjx.vip")
public interface DemoService {

    @WebMethod
    @WebResult(name="String",targetNamespace = "http://chenjx.vip")
    String sayHi(@WebParam(name="text") String  text);


}
