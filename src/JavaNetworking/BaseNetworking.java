package JavaNetworking;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Host: a machine for networking
 * Ethernet: private network for communicating
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
 * Physical Con : sent to the computer from other hosts on the internet will through that connection 通过物理连接
 * .              a computer has a single physical connection to the network
 * Port number  : route the date to the application 数据通过网络发送, 在接收时会通过port端口号来定位数据要传输的目的地APP, 端口号可能被占用
 * .
 * 1. Two app running on the same host can use TCP/IP to communicate with each other
 * 2. The client and server on the same host, use 127.0.0.1 (localhost) to identify the host
 */
public class BaseNetworking {

    /**
     * 1. java.net package: establish and communicate between computers, old version Http protocol !! 不推荐使用
     * 2. Third-party libraries: www.eclipse.org/jetty & Apache HTTPClient
     */

    /**
     * Networking Basics
     * 1. Application Layer: Http, Ftp, Telnet, POP3 编写Java代码所在的层
     * 2. Transport Layer: TCP, UDP 使用port号来map到数据到电脑上指定的process
     * 3. Network Layer: IP address
     * 4. Link Layer: Device driver
     */

    /**
     * 使用IP地址定位传输的PC, TCP/UDP使用16位(0-1023的端口被预留使用)来确定分发到指定的应用
     * Data transmitted over Internet is accompanied by addressing info that identifies computer and port for which it is destined.
     * The computer is identified by its 32-bit IP address, which IP uses to deliver data to the right computer on the network.
     * Ports are identified by a 16-bit number, which TCP and UDP use to deliver the data to the right application
     */

    // Network Interface: Systems often run with multiple active network connections 系统可能同时运行着不同的网络连接
    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netIf : Collections.list(nets)) {
            System.out.println("Display name: " + netIf.getDisplayName());
            System.out.println("Name: " + netIf.getName());
            displaySubInterfaces(netIf);
            System.out.println("\n");
        }
    }

    static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();
        for (NetworkInterface subIf : Collections.list(subIfs)) {
            System.out.println("Sub Interface Display name: " + subIf.getDisplayName());
            System.out.println("Sub Interface Name: " + subIf.getName());
        }
    }

    /**
     * TCP建立连接和断开连接的过程？
     * HTTP协议的交互流程，HTTP和HTTPS的差异，SSL的交互流程？
     * TCP的滑动窗口协议有什么用？
     * HTTP协议都有哪些方法？
     * Socket交互的基本流程？
     * 讲讲tcp协议（建连过程，慢启动，滑动窗口，七层模型）？
     * webservice协议（wsdl/soap格式，与restt办议的区别）？
     * 说说Netty线程模型，什么是零拷贝？
     * TCP三次握手、四次挥手？
     * DNS解析过程？
     * TCP如何保证数据的可靠传输的
     */
}







