package JavaNetworking.HighLevelAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

// TODO: 参考Java_11_Reactive_HTTP_Client技术文档
public class BaseJava11HttpClient {

    public void testGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://test.me"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // 返回请求回复的Html内容
        System.out.println(response.body());
    }

    // TODO: 使用Jackson类库转换Object和JSON
    // <dependency>
    //    <groupId>com.fasterxml.jackson.core</groupId>
    //    <artifactId>jackson-databind</artifactId>
    //    <version>2.9.9.3</version>
    // </dependency>
    //
    // 发送请求的数据格式
    // {
    //    "headers": {
    //        "Content-Length": "43",
    //        "Host": "test.org",
    //        "User-Agent": "Java-http-client/12.0.1"
    //    },
    //    "json": {
    //        "name": "John Doe",
    //        "occupation": "gardener"
    //    },
    //    ...
    //    "url": "https://httpbin.org/post"
    // }
    public void testPost() throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put("occupation", "gardener");
        }};
        // 将对象转换成JSON数据格式
        // var objectMapper = new ObjectMapper();
        // String requestBody = objectMapper.writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://test.org/post"))
                .POST(HttpRequest.BodyPublishers.ofString("requestBody"))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
