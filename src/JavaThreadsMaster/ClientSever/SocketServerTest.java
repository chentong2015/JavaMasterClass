package JavaThreadsMaster.ClientSever;

import JavaThreadsMaster.ClientSever.util.MessageUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

// 服务端的权责：
// 1. 套接字连接管理
// 2. 客户端处理
// 3. 线程策略(使用规则)
// 4. 服务器关闭策略
public class SocketServerTest implements Runnable {

    ServerSocket serverSocket;
    volatile boolean keepProcessing = true;

    public SocketServerTest(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    public void run() {
        System.out.printf("Server Starting\n");
        while (keepProcessing) {
            try {
                System.out.printf("accepting client\n");
                Socket socket = serverSocket.accept();
                System.out.printf("got client\n");
                process(socket);
            } catch (Exception e) {
                handle(e);
            }
        }
    }

    private void handle(Exception e) {
        if (!(e instanceof SocketException)) e.printStackTrace();
    }

    public void stopProcessing() throws IOException {
        keepProcessing = false;
        closeIgnoringException(serverSocket);
    }

    void process(Socket socket) {
        if (socket == null) return;
        try {
            System.out.println("Server: getting message\n");
            String message = MessageUtils.getMessage(socket);
            System.out.printf("Server: got message: %s\n", message);
            Thread.sleep(1000);
            System.out.printf("Server: sending reply: %s\n", message);
            MessageUtils.sendMessage(socket, "Processed: " + message);
            System.out.println("Server: sent\n");
            closeIgnoringException(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeIgnoringException(Socket socket) throws IOException {
        if (socket != null) socket.close();
    }

    private void closeIgnoringException(ServerSocket serverSocket) throws IOException {
        if (serverSocket != null) serverSocket.close();
    }
}
