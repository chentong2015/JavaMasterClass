package JavaNetworking.LowLevelAPI.TcpSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * Socket: one end-point of two-way connection 在client端和server端都有的end-point
 * 1. Socket class for client socket 每个client拥有它自己的socket
 * 2. Use IP address + the port 提供两个信息，构建TCP/IP的通讯方式
 * 3. ServerSocket class for server's socket
 */
public class SocketClient {

    /**
     * 1. 提供要连接的server host的IP地址 127.0.0.1 for local host testing
     * 2. 确定server对应的application开放的端口号, 可搜索app特定的port number
     * 3. 设置Timeout, 在client发送请求结束后，指定的时间段内需要收到Server的回复: 可断开client，或重新发送，或执行别的操作，或提示用户"服务器繁忙"
     */
    // Networking异常是一种IO Exception
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5000)) {
            socket.setSoTimeout(5000); // 设置何理的超时时间 
            handleCommunication(socket);
        } catch (SocketTimeoutException e) {
            System.out.println("The socket timed out, notice user ...");
        }
    }

    private static void handleCommunication(Socket socket) throws IOException {
        BufferedReader receivedStream = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
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
