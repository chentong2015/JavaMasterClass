package JavaNetworking.HighLevelAPI;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 0. Scheme: URI & URL 点中间的名称
 * 1. URI: may not provide enough information to access the resource it identifiers 可能是相对路径，可能无法定位资源
 * 实际例子：
 * ___  https://en.wikipedia.org/wiki/Uniform_Resouce_Identifier
 * ___  scheme:[//user[:password]@host[:port]][/path][?query][#fragment]
 * 组成部分:
 * ___  1.1 scheme
 * ___  1.2 scheme-specific part
 * ___  1.3 authority
 * ___  1.4 user-info
 * ___  1.5 host
 * ___  1.6 port
 * ___  1.7 path
 * ___  1.8 query
 * ___  1.9 fragment
 * 2. URL: includes information to access the resource it identifiers 用来唯一定位web上的资源
 * 一种Http URI, 绝对路径，能够locate and access the resource, 但想相对过时
 * 实际例子：
 * ___  ftp://www.ftp.org/docs/text.txt
 * ___  mailto:user@essai.com
 * 组成部分:
 * ___  2.1 Protocol: Http, FTP, TelNet
 * ___  2.2 Hostname: 主机名(DNS域名解析的名称) & IP Address of server
 * ___  2.3 Port: 服务器要接受的端口号
 * ___  2.4 Path-and-file-name: 名称和资源的路径(server上的相对路径)
 * 3. 通常使用URI, 当要访问资源时，可将URI转换成URL
 */
// URI, URL, URLConnection, HTTPURLConnection
public class UriUrl {

    private URI uri;

    /**
     * Scheme: db
     * Scheme-specific part: //username:password@myserver.com:5000/folder/phones?os=android
     * Authority: username:password@myserver.com:5000
     * User info: username:password 用户认证信息
     * Host: myserver.com 目标主机的名称，或者IPv4 & IPv6地址
     * Port: 5000
     * Path: /folder/phones 相对路径
     * Query: os=android 查询的信息
     * Fragment: samsung 目标资源的子集位置
     */
    private void testURI() {
        try {
            // URI 提供的位置可以是完全不存在的路径，不会报错 ==> 可能不是有效的
            uri = new URI("db://username:password@myserver.com:5000/folder/phones?os=android#samsung");
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Scheme-specific part: " + uri.getSchemeSpecificPart());
            System.out.println("Authority: " + uri.getAuthority());
            System.out.println("User info: " + uri.getUserInfo());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 1. URI is not absolute只提供相对路径的URI不足以定位和访问有效的资源
     * 2. Base URI包含公共的host information部分，在结合不同的URI组成绝对的路径 !!!
     * 3. 根据Base URI和Full URI可以解析出相对的路径 !!
     */
    private void testRelativeRUI() {
        try {
            URI baseURI = new URI("http://username:password@server.com:5000");
            URI relativeURI = new URI("/folder/phones?os=android#samsung");
            URI fullURI = baseURI.resolve(relativeURI);

            URI relativeURI2 = new URI("/catalogue/tvs?manufacturer=samsung");
            URI fullURI2 = baseURI.resolve(relativeURI2);

            URI relativeURI3 = baseURI.relativize(fullURI2); // => relativeURI2
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * URI -> URL, URL -> URI
     * uri中的"Scheme: db"并不是有效的URL中支持的Protocol组成部分, 转换出错
     * URL必须是绝对的路径，确定能够定位和访问到资源
     */
    private void testURL() {
        try {
            URL url = uri.toURL();
            URI uri = url.toURI();
        } catch (MalformedURLException | URISyntaxException exception) {
            System.out.println("URI is not valid");
        }
    }
}
