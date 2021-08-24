package JavaNetworking;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

// Host: a machine for networking
// Ethernet: private network for communicating
// Public IP: what is my ip 找到个人电脑的外网IP 77.153.146.101 => IPv4 -> IPv6

// TODO: TCP的三次握手和四次挥手 ?
// TCP位于7层网络协议中的传输层，负责数据的可靠性传输
// Transmission Control Protocol, two-ways connection, reliable connection with the client/server model
// 1. 通过三次握手来建立TCP连接
//    1.1 client向server发送一个SYN
//    1.2 server接收到之后，返回一个SYN ACK
//    1.3 client接收到SYN ACK之后，再给server发送一个ACK
// 2. 通过4次挥手来断开TCP连接
//    2.1 client向server发送一个FIN
//    2.2 server接收到FIN之后，返回一个ACK (表示接收到了断开请求，client端可以不发送数据了，server可能还有数据正在处理)
//    2.3 server端处理完之后，返回一个FIN (表示可以断开连接了)
//    2.4 client接收到ACK之后，再给server发送一个ACK
public class BaseNetworking {

    // Java Networking 包的选择
    // 1. java.net package: establish and communicate between computers, old version Http protocol !! 不推荐使用
    // 2. Third-party libraries: www.eclipse.org/jetty & Apache HTTPClient

    // 1. Two app running on the same host can use TCP/IP to communicate with each other
    // 2. The client and server on the same host, use 127.0.0.1 (localhost) to identify the host
    // Physical Con : sent to the computer from other hosts on the internet will through that connection 通过物理连接
    // Port number  : route the date to the application 数据通过网络发送, 在接收时会通过port端口号来定位数据要传输的目的地APP, 端口号可能被占用

    /**
     * 使用IP地址定位传输的PC, TCP/UDP使用16位(0-1023的端口被预留使用)来确定分发到指定的应用
     * Data transmitted over Internet is accompanied by addressing info that identifies computer and port for which it is destined.
     * The computer is identified by its 32-bit IP address, which IP uses to deliver data to the right computer on the network.
     * Ports are identified by a 16-bit number, which TCP and UDP use to deliver the data to the right application
     */

    // Network Interface: Systems often run with multiple active network connections
    // 显示系统上同时运行的不同网络连接
    public void testNetworkInterfaces() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netIf : Collections.list(nets)) {
            System.out.println("Display name: " + netIf.getDisplayName());
            System.out.println("Name: " + netIf.getName());
            for (NetworkInterface subIf : Collections.list(netIf.getSubInterfaces())) {
                System.out.println("Sub Interface Display name: " + subIf.getDisplayName());
                System.out.println("Sub Interface Name: " + subIf.getName());
            }
        }
    }
}







