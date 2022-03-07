package JavaNetworking.JavaBase.TcpSocket;

import java.io.*;
import java.net.Socket;

// Message信息编码和加密的工具类
public class MessageUtils {

    public static String getMessage(Socket socket) throws IOException {
        InputStream stream = socket.getInputStream();
        ObjectInputStream inputStream = new ObjectInputStream(stream);
        return inputStream.readUTF();
    }

    public static void sendMessage(Socket socket, String message) throws IOException {
        OutputStream stream = socket.getOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        outputStream.writeUTF(message);
        outputStream.flush();
    }
}
