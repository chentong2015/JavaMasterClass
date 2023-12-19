package JavaNetworking.UdpDatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] bufferReceived = new byte[50]; // buffer如果没有被填满，则会输出默认的填充字符

    /**
     * 1. socket.receive(packet); 阻塞, 直到数据到达, 然后接收处理
     * 2. 并不会建立在server和client之间的end-to-end connection, 不向client发送任何回复
     * 3. UDP Server同时支持发送回复的信息给client
     */
    private void testUDPServer() {
        try {
            socket = new DatagramSocket(5000);
            while (true) {
                packet = new DatagramPacket(bufferReceived, bufferReceived.length);
                socket.receive(packet);
                System.out.print("received text: " + new String(bufferReceived, 0, packet.getLength()));
                responseClient();
            }
        } catch (SocketException exception) {
            System.out.println("Socket Exception");
        } catch (IOException e) {
            System.out.print("IOException:" + e.getMessage());
        }
    }

    /**
     * 从收到的packet获取client的信息：IPAddress + Port Number    ===> 真实场景：UDP不需要提供回复信息
     */
    private void responseClient() {
        try {
            String sendBackString = "From server: " + new String(bufferReceived, 0, packet.getLength());
            byte[] bufferBack = sendBackString.getBytes();
            InetAddress addressClient = packet.getAddress();
            int portClient = packet.getPort();
            packet = new DatagramPacket(bufferBack, 0, bufferBack.length, addressClient, portClient);
            socket.send(packet);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
