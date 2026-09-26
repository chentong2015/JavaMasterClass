package core_string.bytes;

// 一个Byte(8 bits)支持的十进制范围是[-128,127]
public class BaseByte {

    public static void main(String[] args) {
        byte by = 98; // int十进制对应的字节编码
        System.out.println(by);        // 输出十进制值
        System.out.println((char) by); // 输出对应字符b

        byte minByte = Byte.MIN_VALUE; // -128
        System.out.println((char) minByte);

        byte maxByte = Byte.MAX_VALUE - 1;  // 126
        System.out.println((char) maxByte); // ～

    }
}
