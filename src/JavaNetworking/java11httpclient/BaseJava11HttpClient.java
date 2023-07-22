package JavaNetworking.java11httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

// TODO: 参考Java_11_Reactive_HTTP_Client技术文档
public class BaseJava11HttpClient {

    public void testClientGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://test.me"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // 返回请求回复的Html内容
        System.out.println(response.body());
    }

    public void testClientPost() throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put("occupation", "gardener");
        }};
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://test.org/post"))
                .POST(HttpRequest.BodyPublishers.ofString("requestBody"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void testClientPostAsync() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/v1/domain/sudommain/async"))
                .GET()
                .build();
        // 异步发送请求，不会阻塞
        // String result = httpClient.sendAsync(request, responseInfo -> new EventSubscriber())
        //         .whenComplete((response, status) -> System.out.println(response.statusCode()))
        //         .thenApply(HttpResponse::body)
        //         .join(); 在获取返回的数据的时候，会造成阻塞的情况
        // System.out.println(result);
    }
}
