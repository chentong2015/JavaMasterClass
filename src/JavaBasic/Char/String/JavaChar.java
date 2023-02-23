package JavaBasic.Char.String;

// char -> Character 16 bits 对应16位的"Unicode编码表 0000-0FFF(可编65536字符)"
//      -> ASCII 8 bits 该编码方案取的是低八位的字符码对应关系表
public class JavaChar {

    private final static int MY_INT = 10;

    private void testJavaChar() {
        char myChar = 'D';
        char offsetChar = 'A' + 15;               // 1. 使用常量进行偏移量计算, 隐式转换 !!
        char constChar = 'A' + MY_INT;

        int offset = 10;
        char convertChar = (char) ('A' + offset); // 2. 使用变量进行偏移计算，显示强制转换 !!
        char convertChar2 = (char) (65 + offset);
        char myUnicodeChar = '\u0044';            // \\u 表示码位的表示
        char myCopyRightUnicodeChar = '\u00A9';   // 0x00A9 十六进制写法

        // boolean -> Boolean
        boolean myTureBooleanValue = true;
    }
}
