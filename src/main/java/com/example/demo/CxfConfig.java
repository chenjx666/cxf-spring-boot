package com.example.demo;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private DemoService demoService;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint=new EndpointImpl(bus,demoService);
        endpoint.publish("/DemoService");
        endpoint.getInInterceptors().add(new AuthInterceptor());
        return endpoint;
    }

    /**
     * 配置路径
     * 默认services
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new CXFServlet(), "/services/*");
        bean.setLoadOnStartup(0);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
