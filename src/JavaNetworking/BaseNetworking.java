package JavaNetworking;

/**
 * Host: a machine for networking
 * Inteanets: private network for communicating
 * Public IP: what is my ip 找到个人电脑的外网IP 77.153.146.101 => IPv4 -> IPv6
 * .
 * Client/Server: one or more hosts acting as servers, and other hosts (browser) are clients that connect to server
 * TCP / UDP: network transport protocol
 * TCP: Transmission Control Protocol, two-ways connection, reliable connection with the client/server model
 * ___  The client opens a connection to the server
 * ___  The clients sends a request to the server
 * ___  The server sends a response to the client
 * ___  The client closes the connection to the server
 * .
 * SQL Database : the workbench is client, it connects to the SQL database server 数据库服务器
 * Local Web Dev: connect an Apache or IIS server on your computer using a browser
 * Physical Con: sent to the computer from other hosts on the internet will through that connection 通过物理连接
 * Port number ：route the date to the application 数据通过网络发送, 在接收时会通过port端口号来定位数据要传输的目的地APP, 端口号可能被占用
 * .
 * 1. Two app running on the same host can use TCP/IP to communicate with each other
 * 2. The client and server on the same host, use 127.0.0.1 (localhost) to identify the host
 */
public class BaseNetworking {

    /**
     * 1. java.net package: establish and communicate between computers, using abstract concepts
     * ___ For JDK 1.0, Old version Http protocol !! 不推荐使用
     * .
     * 2. Third-party libraries 推荐的第三方HTTP类库   ===>  HTTP : Stateless protocol 无状态的传输协议
     * ___ Jetty : https://www.eclipse.org/jetty
     * ___ Apache HTTPClient: http://hc.apache.org/httpcomponents-client-ga/
     */

    /**
     * TODO: networking 进阶理解
     * https://docs.oracle.com/javase/tutorial/networking/overview/networking.html
     * https://docs.oracle.com/javase/tutorial/networking/urls/index.html
     * https://docs.oracle.com/javase/tutorial/networking/nifs/index.html
     * https://docs.oracle.com/javase/tutorial/networking/cookies/definition.html   ===> Cookie在Java Web中的使用
     */

    /**
     * TODO: URL的编码和解码
     * www.url-encode-decode.com
     * www.w3school.com/tags/ref_urlencode.ASP (Active Server Page)
     */
}







