package JavaNetworking;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseHttpUrlConnection {

    // connection.connect()
    // Java会通过low-level api来创建socket实现背后的逻辑
    private void testGetRequest() {
        try {
            URL url = new URL("http://example.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // Can write to the connection
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome"); // 用户代理: 声明使用的浏览器或者script
            // connection.connect(); 多余的connect，没有任何的效果

            connection.setReadTimeout(3000); // Waiting for server if its slow
            if (connection.getResponseCode() != 200) { // Perform connection 构建connect然后发送请求 !!
                System.out.println(connection.getResponseMessage()); // 返回请求的错误信息
            } else {
                readGetRequestResponse(connection);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // connection.getInputStream(); Implicitly执行连接拿到发送请求，返回结果
    private void readGetRequestResponse(HttpURLConnection connection) {
        try (InputStream inputStream = connection.getInputStream();
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = inputReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = inputReader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 可添加发送request的header fields：List_of_HTTP_header_fields <key, value>
    private void testPostRequest() {
        try {
            URL url = new URL("http://example.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setUseCaches(true); // 允许该连接使用其可以使用的任何缓存, 默认值来自defaultUseCaches，默认为true

            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer" + "token");
            // connection.connect(); 多余的connect，没有任何的效果

            String parameters = "parameters1=25&parameters2=hello";
            connection.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(parameters);
            output.flush();
            output.close();

            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
