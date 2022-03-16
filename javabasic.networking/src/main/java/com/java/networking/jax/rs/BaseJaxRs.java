package com.java.networking.jax.rs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

// JAX-RS: javax restful web service
// 提供后端API的实现, 同时包含Client API
// https://gayerie.dev/epsi-poe-201703/javaee/11_jaxrs.html

// Provide interfaces SseEvent and InboundSeeEvent to read SSE
// 1. Request Header for SSE ?
// 2. Reconnect Support by Last-Event-ID
public class BaseJaxRs {

    // Jax.Rs Client 发送请求，获取到指定类型的返回数据
    public void testJaxRsClient() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.server.net/person");
        // Person person = target.request().get(Person.class);
    }
}
