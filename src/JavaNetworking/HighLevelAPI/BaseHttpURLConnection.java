package JavaNetworking.HighLevelAPI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Support for HTTP specific features
 * Request Method + response code
 * 1. GET: code 200 = Ok, code 404 = server cannot find the resources, 500 = something critical wrong
 * 2. POST: provide info to URL
 * 3. HEAD, OPTIONS
 * 4. PUT, DELETE, TRACE
 * https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html
 */
public class BaseHttpURLConnection {

    // connection.connect() Java会通过low-level api来创建socket实现背后的逻辑
    // 添加发送request的header fields：List_of_HTTP_header_fields <key, value>
    private void testHttpURLConnection() {
        try {
            URL url = new URL("http://example.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Chrome"); // 用户代理: 声明使用的浏览器或者script
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer" + "token");


        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
