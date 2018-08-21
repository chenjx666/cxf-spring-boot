package com.example.demo;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * 客户端需要在header中添加用户信息，这样才能通过服务器的验证。
 */
public class ClientLoginInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private String username;
    private String password;

    public ClientLoginInterceptor(String username, String password) {
        super(Phase.PREPARE_SEND);
        this.username = username;
        this.password = password;
    }

    @Override
    public void handleMessage(SoapMessage soap) throws Fault {
        List<Header> headers = soap.getHeaders();
        Document doc = DOMUtils.createDocument();
        Element auth = doc.createElement("authrity");
        Element username = doc.createElement("username");
        Element password = doc.createElement("password");
        username.setTextContent(this.username);
        password.setTextContent(this.password);
        auth.appendChild(username);
        auth.appendChild(password);
        headers.add(0, new Header(new QName("tiamaes"), auth));

    }

}