package JavaNetworking.TcpSocket;

import java.io.*;
import java.net.Socket;

public class SocketServerThread extends Thread {

    private Socket socket;

    public SocketServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            processSocket();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeSocket();
        }
    }

    private void processSocket() throws IOException {
        InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader receivedStream = new BufferedReader(streamReader);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter sendBackStream = new PrintWriter(outputStream, true);
        while (true) {
            String receivedString = receivedStream.readLine();
            if (receivedString.equals("exit")) {
                break;
            }
            String sendBackString = "Send back: " + receivedString;
            sendBackStream.println(sendBackString);
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
