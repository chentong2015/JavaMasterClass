package core_types.integer;

public class JavaIntBinary {

    // TODO. Int整型数据实际存储的Binary二进制形式
    public static void main(String[] args) {
        // int类型分配4个字节的内存空间, 值从低位开始存储
        // 0000 0000 0000 0000 0000 0000 0000 1010
        int value = 10;

        int t = 15;
        System.out.println(Integer.toBinaryString(t));

        int tt = t & 0xFF;
        System.out.println(Integer.toBinaryString(tt));

        // 二进制的与运算后会自动转成10进制输出
        System.out.println(t & 0xFF);

        int x = 922342959;
        display(x >>> 24);
        display(x >>> 16);
        display(x >>> 8);
        display(x >>> 1);
    }

    private static void display(int x) {
        // 二进制的高位补0
        String all = String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0");
        String colouredBinary =  all.substring(0, 24) + all.substring(24);

        int y = x & 0xFF;
        // 0000 0000 1111 1111 = 0x00FF
        // Integer.toBinaryString(y) 将int转成bits输出，格式成%8s
        String formatString = "%10d and 0xFF is %8s \t %10d is ";
        String output = String.format(formatString, y, Integer.toBinaryString(y), x) + colouredBinary;
        System.out.println(output);
    }
}
