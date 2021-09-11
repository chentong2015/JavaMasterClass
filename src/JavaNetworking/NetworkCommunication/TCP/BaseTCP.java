package JavaNetworking.NetworkCommunication.TCP;

// TODO: TCP的三次握手和四次挥手 ?
// TCP位于7层网络协议中的传输层，负责数据的可靠性传输
// Transmission Control Protocol, two-ways connection, reliable connection with the client/server model
// 1. 通过三次握手来建立TCP连接
//    1.1 client向server发送一个SYN
//    1.2 server接收到之后，返回一个SYN ACK
//    1.3 client接收到SYN ACK之后，再给server发送一个ACK
// 2. 通过4次挥手来断开TCP连接
//    2.1 client向server发送一个FIN
//    2.2 server接收到FIN之后，返回一个ACK(表示接收到了断开请求，client端可以不发送数据了，server可能还有数据正在处理)
//    2.3 server端处理完之后，返回一个FIN(表示可以断开连接了)
//    2.4 client接收到ACK之后，再给server发送一个ACK
public class BaseTCP {
}
