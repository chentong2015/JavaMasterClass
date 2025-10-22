package char_encoding;

// 测试码位的表示和操作
public class AsciiCharCode {

    // TODO. 使用\\u 来表示码位的表示
    public static void main(String[] args) {
        // ASCII 0011 位置对应的char字符
        char unicodeChar1 = '\u0003';
        System.out.println(unicodeChar1);

        // Ox0044 十六进制写法 -> 对应字符‘D'
        char unicodeChar2 = '\u0044';
        System.out.println(unicodeChar2);

        // 0x00A9 十六进制写法 -> 对应'©'字符
        // 该字符超过ASCII的编码字符范围，属于Unicode字符集中的字符
        char unicodeChar3 = '\u00A9';
        System.out.println(unicodeChar3);

        // 将控制字符和字符串拼接  NULL
        String ascii01 = "\u0001 NULL";
        System.out.println(ascii01);
    }

    // 通过码位表示特定的字符串输出颜色
    private static void charCodeSStringColor() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        System.out.println(ANSI_RED + "red console info");
        System.out.println(ANSI_BLUE + "blue console info");
    }
}
