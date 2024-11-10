package JavaBasic.Char;

import java.nio.charset.StandardCharsets;

public class JavaChar {

    private final static int MY_INT = 10;

    public static void main(String[] args) {
        char myChar = 'D';
        char offsetChar = 'A' + 15; // 1. 使用常量进行偏移量计算, 隐式转换 !!
        char constChar = 'A' + MY_INT;

        int offset = 10;
        char convertChar = (char) ('A' + offset); // 2. 使用变量进行偏移计算，显示强制转换 !!
        char convertChar2 = (char) (65 + offset);

        char myUnicodeChar = '\u0044';  // \\u 表示码位的表示
        char myCopyRightUnicodeChar = '\u00A9'; // 0x00A9 十六进制写法

        // à 包含两个unicode的字符
        System.out.println("éà".charAt(1));
        System.out.println("éà".getBytes(StandardCharsets.UTF_8).length);

        // ASCII 0011 位置对应的char字符
        char c = '\u0003';
        System.out.println(c);
    }
}
