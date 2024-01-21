package JavaNetworking.java11httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;

// 参考Java_11_Reactive_HTTP_Client技术文档
public class HttpClientAsyncRequest {

    // 异步发送请求，不会阻塞Client
    public void testClientPostAsync() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/v1/domain/sudommain/async"))
                .GET()
                .timeout(Duration.ofMinutes(3))
                .build();
        String result = httpClient.sendAsync(request, responseInfo -> new EventSubscriber())
                .whenComplete((response, status) -> System.out.println(response.statusCode()))
                .thenApply(HttpResponse::body)
                .join(); // 在获取返回的数据的时候，会造成阻塞的情况
        System.out.println(result);
    }

    // 异步的Event事件订阅器，异步等待请求的结果
    class EventSubscriber implements HttpResponse.BodySubscriber<String> {

        private CompletableFuture<String> bodyCF = new CompletableFuture<>();
        private List<ByteBuffer> responseData = new ArrayList<>();
        private Flow.Subscription s;
        private long size;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.s = subscription;
            s.request(1);
        }

        @Override
        public void onNext(List<ByteBuffer> buffer) {
            responseData.addAll(buffer);
            size += buffer.stream().mapToInt(ByteBuffer::remaining).sum();
            if (size > 1000) {
                String response = "null"; // = getBufferContents(responseData);
                bodyCF.complete(response);
                s.cancel();
            } else {
                String bufferContents = "buffer contents"; // = getBufferContents(buffer);
                System.out.println(bufferContents);
                s.request(1);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            bodyCF.completeExceptionally(throwable);
        }

        @Override
        public void onComplete() {
            // On complete, to do something
        }

        @Override
        public CompletionStage<String> getBody() {
            return null;
        }
    }
}
