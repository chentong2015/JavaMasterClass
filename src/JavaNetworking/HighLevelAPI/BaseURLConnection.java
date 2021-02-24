package JavaNetworking.HighLevelAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class BaseURLConnection {

    private BufferedReader inputStream;
    private URLConnection urlConnection;

    /**
     * URL: 可以提供protocol, host, port, file; 在访问API或者web service: 直接提供http web address, parameters
     * url.openStream() 包含两步: open URLConnection, get InputStream 返回请求URL的返回值
     */
    private void testURLConnection() {
        try {
            URL url = new URL("http://example.org");
            inputStream = new BufferedReader(new InputStreamReader(url.openStream()));
            readInputStream();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    // Get: html web page code sources
    private void readInputStream() throws IOException {
        String line = "";
        while (line != null) {
            line = inputStream.readLine();
            System.out.println(line);
        }
        inputStream.close();
    }

    private void testURLConnectionClass() {
        try {
            URL url = new URL("http://example.org");
            urlConnection = url.openConnection();
            urlConnection.setDoInput(true); // 将URL连接用于输入
            urlConnection.setDoOutput(true); // 将URL连接用于输出: 确保在open Connection之后，在connect之前设置
            urlConnection.connect();

            inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            readInputStream();
        } catch (MalformedURLException exception) {
            System.out.println("Malformed url exception");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    private void processWebInformation() {
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            System.out.println("Key = " + entry.getKey());
            for (String str : entry.getValue()) {
                System.out.println("Value = " + str);
            }
        }
    }
}
