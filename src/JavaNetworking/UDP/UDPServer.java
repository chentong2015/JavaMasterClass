package JavaNetworking.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

    /**
     * socket.receive(packet);
     * 1. 阻塞, 直到数据到达, 然后接收处理
     * 2. 并不会建立在server和client之间的end-to-end connection, 不向client发送任何回复
     */
    private void testUDPServer() {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // 阻塞, 直到数据到达, 然后接收处理
                System.out.print("received text: " + new String(buffer));
            }
        } catch (SocketException exception) {
            System.out.println("Socket Exception");
        } catch (IOException e) {
            System.out.print("IOException:" + e.getMessage());
        }
    }
}
