package JavaNetworking.TcpSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5000)) {
            socket.setSoTimeout(5000);
            handleCommunication(socket);
        } catch (SocketTimeoutException e) {
            System.out.println("The socket timed out, notice user ...");
        }
    }

    private static void handleCommunication(Socket socket) throws IOException {
        BufferedReader receivedStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter sendStream = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter string to be echoed: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
               break;
            }
            sendStream.println(input);
            System.out.println("Received from server: " + receivedStream.readLine());
        }
    }
}
