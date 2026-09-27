package core_types.bytes;

// TODO. Byte本质是带符号的整数, 表示一个字节长度[-128,127]
public class BaseByte {

    public static void main(String[] args) {
        byte by = 98; // int十进制整数值
        System.out.println(by);        // 输出十进制值
        System.out.println((char) by); // 输出整数对应字符

        byte minByte = Byte.MIN_VALUE; // -128
        System.out.println((char) minByte);

        byte maxByte = Byte.MAX_VALUE - 1;  // 126
        System.out.println((char) maxByte); // ～

        System.out.println(Integer.toBinaryString(by)); // 输出整数的二进制存储形式
    }
}