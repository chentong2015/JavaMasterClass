package JavaNetworking;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

// TODO: 访问资源时，通常使用URI，同时可以将其转换成URL
// 1. URI: may not provide enough information to access the resource it identifiers 可能是相对路径，可能无法定位资源
//    实际例子：https://en.wikipedia.org/wiki/Uniform_Resouce_Identifier
//            scheme:[//user[:password]@host[:port]][/path][?query][#fragment]
//    组成部分: 1.1 scheme
//            1.2 scheme-specific part
//            1.3 authority
//            1.4 user-info
//            1.5 host
//            1.6 port
//            1.7 path
//            1.8 query
//            1.9 fragment

// 2. URL: includes information to access the resource it identifiers 用来唯一定位web上的资源
//         一种Http URI, 绝对路径，能够locate and access the resource 但相对过时
//   实际例子：ftp://www.ftp.org/docs/text.txt
//           mailto:user@essai.com
//   组成部分: 2.1 Protocol: Http, FTP, Telnet
//           2.2 Hostname: 主机名(DNS域名解析的名称) & IP Address of server
//           2.3 Port: 服务器要接受的端口号 (默认端口8080可以显式写出来)
//           2.4 Path-and-file-name: 名称和资源的路径(server上的相对路径)
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
     * URL必须是绝对的路径，确定能够定位和访问到资源 !!
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
