package com.java.networking.apache;

import org.apache.http.*;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpRequestExecutor;

import java.io.IOException;

public class ApacheHttpRequestExecutor extends HttpRequestExecutor {

    // 自定义Apache HttpRequestExecutor 请求的执行器在执行时的配置
    @Override
    public HttpResponse execute(HttpRequest request,
                                HttpClientConnection conn,
                                HttpContext context) throws IOException, HttpException {
        String index = getIndexFromHeader(request);
        // 获取到http request target host
        HttpHost httpHost = (HttpHost) context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
        String endpoint = httpHost.toHostString();
        long startTime = System.nanoTime();
        try {
            HttpResponse response = super.execute(request, conn, context);
            int statusCode = response.getStatusLine().getStatusCode();
            // update result
            return response;
        } catch (Exception e) {
            // update result
            throw e;
        }
    }

    // 从请求的header中获取信息
    private String getIndexFromHeader(HttpRequest request) {
        Header[] indexHeaders = request.getHeaders("header-index");
        if (indexHeaders != null && indexHeaders.length > 0) {
            return indexHeaders[0].getValue();
        }
        return null;
    }
}
