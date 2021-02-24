package JavaNetworking.LowLevelAPI.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        BufferedReader receivedStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendBackStream = new PrintWriter(socket.getOutputStream(), true);
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
