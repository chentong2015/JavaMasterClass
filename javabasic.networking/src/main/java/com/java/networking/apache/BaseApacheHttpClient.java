package com.java.networking.apache;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// TODO: 推荐使用Apache HttpClient类库提供Http请求
// https://hc.apache.org/httpcomponents-client-4.5.x/index.html
// https://hc.apache.org/httpcomponents-client-4.5.x/current/tutorial/html/fundamentals.html

// Apache Http Client Async异步发送请求
// https://hc.apache.org/httpcomponents-asyncclient-4.1.x/index.html

// https://mkyong.com/java/apache-httpclient-examples/
// https://www.baeldung.com/httpclient-post-http-request
public class BaseApacheHttpClient {

    public void testHttpClientsSync() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://example.org");
        request.addHeader("User-Agent", "Chrome");

        CloseableHttpResponse response = httpClient.execute(request);
        try {
            System.out.println("response code = " + response.getStatusLine().getStatusCode());
            InputStream inputStream = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            response.close();
        }
    }

    // 使用Apache HttpClientBuilder来构建自定义的Http Client
    public void testHttpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setDefaultCookieStore(new BasicCookieStore());
        httpClientBuilder.disableAuthCaching();
        // ...
    }
}
