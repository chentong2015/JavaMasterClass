package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 2. NIO(Non block IO, New IO)
public class BaseNIOServer {

    private static List<SocketChannel> channelList = new ArrayList<>();

    // TODO: NIO不足之处  ==> 使用Selector多路复用器进行优化
    // 1. 不停的循环，可能造成CPU过高
    //    > 如果没有和客户端收发信息，则可以让出CPU的执行时间
    // 2. 每次都需要遍历list中的SocketChannel，造成不必要性能浪费
    //    > 将有数据收发连接放到一个单独的list，只需要遍历该集合
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 设置server端为非阻塞
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) { // 确定有连接并且连接成功
                System.out.println("Connection OK");
                // 同样设置成非阻塞
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer buffer = ByteBuffer.allocate(128);
                // 在非阻塞模式下不会被阻塞
                int len = sc.read(buffer);
                if (len > 0) { // 确定有接受到来自客户端的数据
                    System.out.println("Receive: " + new String(buffer.array()));
                } else if (len == -1) {
                    sc.close();
                    iterator.remove();
                }
            }
        }
    }
}
