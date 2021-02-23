package JavaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
 * .
 * java.net package: establish and communicate between computers, using abstract concepts
 * Socket: one end-point of two-way connection 在client端和server端都有的end-point
 * ___  Socket class for client socket 每个client拥有它自己的socket
 * ___  Use IP address + the port 提供两个信息，构建TCP/IP的通讯方式
 * ___  ServerSocket class for server's socket
 * 1. Low-level API: use sockets to establish connections, send requests, and receive responses
 * 2. High-level API:
 */
public class BaseNetworking {

    /**
     * Networking的异常是一种IO Exception
     * 127.0.0.1 for local host testing 提供要连接的server host的IP地址
     * 确定server对应的application开放的端口号, 可搜索app特定的port number
     */
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader receivedStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sendStream = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String scannerString;
            do {
                System.out.println("Enter string to be echoed: ");
                scannerString = scanner.nextLine();
                if (!scannerString.equals("exit")) {
                    sendStream.println(scannerString);
                    System.out.println("Received from server: " + receivedStream.readLine());
                }
            } while (!scannerString.equals("exit"));
        }
    }
}







