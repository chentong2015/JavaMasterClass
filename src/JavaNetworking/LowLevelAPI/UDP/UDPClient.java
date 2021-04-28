package JavaNetworking.LowLevelAPI.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * UDP: User Datagram Protocol 用户数据报协议
 * 1. 不需要双向连接 two-way connections, 在一定程度上能提高性能, 提升速度
 * 2. 不确保通讯的连接 not reliable, 目标host(may not be a server)不需要给发送者回复responses
 * 3. UDP使用Datagram, datagram is a self-contained message 不确保能够发送到目的地
 * 4. 多用在time-sensitive communication, 丢失旧信息或者packet包不会造成太大的影响 ==> voice over IP App: Skype & video Streaming
 */
public class UDPClient {

    /**
     * Send data using a datagram (independent packets of data)
     * 1. 使用DatagramSocket来进行数据传输packet包, 指定包传输的address和port端口号
     * 2. 发送的顺序不被保证  The order of delivery is not guaranteed, each message is independent of any other
     * 3. 提供要发送的host名称, InetAddress.getByName() & InetAddress.getLocalHost()
     */
    private void testUDPClient() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String inputString;
            do {
                System.out.println("Enter string to server host...");
                inputString = scanner.nextLine();
                byte[] buffer = inputString.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000); // self-contained 里面包含传输的目的地
                socket.send(packet);

                // Get back info from UDP Server
                byte[] bufferReceived = new byte[50];
                packet = new DatagramPacket(bufferReceived, bufferReceived.length);
                socket.receive(packet);
                System.out.print("received text: " + new String(bufferReceived, 0, packet.getLength()));
            } while (!inputString.equals("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("The socket timed out");
        } catch (IOException exception) {
            System.out.println("client error" + exception.getMessage());
        }
    }
}
