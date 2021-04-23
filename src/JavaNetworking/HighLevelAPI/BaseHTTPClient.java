package JavaNetworking.HighLevelAPI;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// http://hc.apache.org/downloads.cgi
// http://www.slf4j.org/codes.html#StaticLoggerBinder add slf4j-simple-1.7.25.jar 补充类库

// TODO: https://howtodoinjava.com/java/library/jaxrs-client-httpclient-get-post/
public class BaseHTTPClient {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://example.org");
        request.addHeader("User-Agent", "Chrome");
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            System.out.println("response code = " + response.getCode());

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
}
