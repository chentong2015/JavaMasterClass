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
     * java.net package: establish and communicate between computers, using abstract concepts
     *  Socket: one end-point of two-way connection 在client端和server端都有的end-point
     *  ___  Socket class for client socket 每个client拥有它自己的socket
     *  ___  Use IP address + the port 提供两个信息，构建TCP/IP的通讯方式
     *  ___  ServerSocket class for server's socket
     *  .
     *  1. Low-level API: use sockets/port to establish connections, send requests, and receive responses
     *  2. High-level API: URI (universal resource identifier) & URL (universal resource locator)
     */

    /**
     * TODO: 看Java doc介绍，测试服务器，理解分布式，负载均衡 (最大线程数)
     */
}







