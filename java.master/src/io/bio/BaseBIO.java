package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 1. BIO(Blocking IO) 阻塞IO >> 不支持高并发
//    client --> Thread -->
//    client --> Thread --> Server
//    client --> Thread -->
public class BaseBIO {

    // TODO: BIO无法支持高并发场景
    public void testBaseBIO() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            // 阻塞：直到有客户端来连接
            Socket clientSocket = serverSocket.accept();
            System.out.printf("Client connected.");
            handleSocket(clientSocket);
        }
    }

    private void handleSocket(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        // 阻塞：直到拿到客户端发送的消息
        int value = socket.getInputStream().read(bytes);
        if (value != -1) {
            System.out.println("Receive: " + value);
        }
    }

    // TODO: 将接受客户端请求的逻辑放到新线程，可以避免阻塞主线程，使得主线程再次接受新客户端连接
    // 1. 如果连接的客户端过多，会导致后台开的线程过多，内存爆炸
    //    C10K: connection 10*1000 一万连接数的问题
    //    C10M: connection 10*1M   一千万连接的问题
    // 2. 如果使用线程池，又受到线程池的线程数量限制，无法处理超过一定数量的线程数
    public void testBaseBIO2(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.printf("Client connected.");
            new Thread(() -> {
                try {
                    handleSocket(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
