package JavaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class BaseUrlConnection {

    private BufferedReader inputStream;
    private URLConnection urlConnection;

    private void testURLConnection() {
        try {
            // URL可以提供protocol, host, port, file;
            URL url = new URL("http://example.org");
            // url.openStream(): open URLConnection, get InputStream 返回请求URL的返回值
            inputStream = new BufferedReader(new InputStreamReader(url.openStream()));
            readInputStream();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    // Get: html web page code sources 可以对网页的源码进行正则表达式的解析
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
            urlConnection.setDoInput(true);  // 将URL连接用于输入: 默认值为true
            urlConnection.setDoOutput(true); // 将URL连接用于输出: 确保在open Connection之后，在connect之前设置
            urlConnection.connect();
            // .getInputStream() 建立连接，获得返回的数据
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

    // 向指定的URL路径发送URL请求，注意设置"content-type"
    public URLConnection makeGetUrl(String strFile, String action) throws Exception {
        URLConnection urlConnection = new URL("http://example.org").openConnection();
        urlConnection.setDoOutput(false);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("TONG-EXTENSION", "TONG");
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection) urlConnection).setRequestMethod("GET");
        }

        urlConnection.setRequestProperty("TONG-ACTION", action);
        urlConnection.setRequestProperty("content-length", "0");
        urlConnection.setRequestProperty("content-type", "application/zip");
        return urlConnection;
    }

    public URLConnection makePostUrl(String strFile, String action, String contentType, byte[] content) throws Exception {
        URLConnection urlConnection = new URL("http://example.org").openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("TONG-EXTENSION", "TONG");
        urlConnection.setRequestProperty("TONG-ACTION", "WRITE_FILE");
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection) urlConnection).setRequestMethod("POST");
        }

        urlConnection.setRequestProperty("content-length", Integer.toString(content.length));
        urlConnection.setRequestProperty("content-type", contentType);
        return urlConnection;
    }
}
