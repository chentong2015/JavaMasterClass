package JavaIO.NIOPackage.SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServerSocketChannel {

    public void testServerSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        // 设置成非阻塞的监听形式
        serverSocketChannel.configureBlocking(false);

        while (true) {
            // 监听Socket连接信息，默认会阻塞
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                //do something with socketChannel...
            }
        }
    }
}
