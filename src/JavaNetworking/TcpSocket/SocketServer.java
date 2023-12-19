package JavaNetworking.TcpSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    // Single BaseThread Server: ServerSocket只允许一个Client连接
    // 1. 指定的Application server的端口号在1-65535之间, 但是不能被其他应用占有: 特殊app占有特殊的端口号
    //    serverSocket.accept();
    // 2. Waiting for clients connect
    // 3. Create an end-to-end connection 创建可靠的连接
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            // Blocked: 用来和server联系的socket，server port一致，client port不一致
            Socket socket = serverSocket.accept();
            System.out.println("New client connect ...");
            BufferedReader receivedStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // autoFlush: 刷新输出, 确保数据已经被发送
            PrintWriter sendStream = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                // Blocked: 如果没有收到信息，ServerSocket会在这里阻塞
                String receivedString = receivedStream.readLine();
                if (receivedString.equals("exit")) break;
                String sendBackString = "Send back: " + receivedString;
                sendStream.println(sendBackString);
            }
        }
    }

    /**
     * Multi BaseThread Server: 服务器需要支持多个Clients的连接，高并发的处理请求
     * 1. Server需要根据每一个Client的连接，创建一个相应的Socket来处理，
     * 2. 每个Socket独立处理各自的client请求，完成数据的收发
     * 3. 当有Client关闭连接之后，对应的Server socket应该停止运行
     */
    // 问题1：当多个Client都exit的时候，ServerSocket没有办法关闭，还在while循环
    // 问题2: receivedStream.readLine(); 由于这里Blocked，当第二个Client连接的时候，没有办法创建相应的socket
    // 问题3: serverSocket.accept();     由于这里Blocked, 当client再次连接的时候，只能收到一次的返回信息
    private void testSocketServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connect ...");
                BufferedReader receivedStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter sendStream = new PrintWriter(socket.getOutputStream(), true);

                String receivedString = receivedStream.readLine();
                try {
                    Thread.sleep(1000); // 解决.accept()造成的阻塞，需要时间切换到别的Client
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                if (receivedString.equals("exit")) {
                    break;
                }
                String sendBackString = "echo from server: " + receivedString;
                sendStream.println(sendBackString);
            }
        }
    }

    /**
     * Multi BaseThread Server: 服务器需要为每个成功连接的Client创建新的线程来处理
     * 1. 解决由于Server单线程造成的Socket的阻塞
     * 2. 支持相应式的client连接，同时避免一个Client独占Server过长的时间，导致无法处理别的Client请求
     * 3. 新的线程负责server's input/output Streams, listening for requests on the client, responding to events
     * 4. Client断开连接时，server对应创建的socket.close()，导致对应的Thread线程结束全部运行
     */
    private void testMultiThreadServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                // 当有新的Client连接成功的时候，Blocking阻塞会取消，然后为Client创建一个新的处理线程
                SocketServerThread serverThread = new SocketServerThread(socket);
                serverThread.start();
            }
        }
    }
}
