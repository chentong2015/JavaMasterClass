package JavaNetworking.LowLevelAPI.TCP.SocketDemo;

import java.io.*;
import java.net.Socket;

public class MessageUtils {

    public static void sendMessage(Socket socket, String message) throws IOException {
        OutputStream stream = socket.getOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        outputStream.writeUTF(message);
        outputStream.flush();
    }

    public static String getMessage(Socket socket) throws IOException {
        InputStream stream = socket.getInputStream();
        ObjectInputStream inputStream = new ObjectInputStream(stream);
        return inputStream.readUTF();
    }
}
