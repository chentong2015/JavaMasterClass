package JavaNetworking.master.apache;

// TODO: 推荐使用Apache HttpClient类库提供Http请求
// https://hc.apache.org/httpcomponents-client-4.5.x/index.html
// https://hc.apache.org/httpcomponents-client-4.5.x/current/tutorial/html/fundamentals.html

// https://mkyong.com/java/apache-httpclient-examples/
// 1. Send GET Request
// 2. Send Normal POST Request
// 3. Send JSON POST Request
// 4. HTTP Basic Authentication
// https://www.baeldung.com/httpclient-post-http-request
public class BaseApacheHttpClient {

    public void testHttpClientsSync() {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet request = new HttpGet("http://example.org");
        // request.addHeader("User-Agent", "Chrome");
        // CloseableHttpResponse response = httpClient.execute(request);
        // try {
        //     System.out.println("response code = " + response.getCode());
        //     InputStream inputStream = response.getEntity().getContent();
        //     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //     String line = reader.readLine();
        //     while (line != null) {
        //         System.out.println(line);
        //         line = reader.readLine();
        //     }
        //     reader.close();
        // } catch (IOException exception) {
        //     exception.printStackTrace();
        // } finally {
        //     response.close();
        // }
    }

    // Apache Http Client Async异步发送请求
    // https://hc.apache.org/httpcomponents-asyncclient-4.1.x/index.html
}
