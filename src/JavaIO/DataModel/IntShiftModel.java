package JavaIO.DataModel;

public class IntShiftModel {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        int t = 15;
        int tt = t & 0xFF;
        System.out.println(t & 0xFF);
        System.out.println(Integer.toBinaryString(tt));

        int x = 922342959;
        writeInt(x);
    }

    private static void writeInt(int v) {
        int x;
        display(v >>> 24);
        display(v >>> 16);
        display(v >>> 8);
        display(v >>> 0);
    }

    private static void display(int x) {
        String all = String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0"); // 二进制的高位补0
        String colouredBinary = all.substring(0, 24) + ANSI_PURPLE + all.substring(24) + ANSI_RESET;  // 给String字符串添加颜色
        int y = x & 0xFF;
        // 0000 0000 1111 1111 = 0x00FF
        // Integer.toBinaryString(y) 将int转成bits输出，格式成%8s
        String output = String.format("%10d and 0xFF is %8s \t %10d is ", y, Integer.toBinaryString(y), x) + colouredBinary;
        System.out.println(output);
    }

}
