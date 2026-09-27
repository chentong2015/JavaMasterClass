package core_char;

// TODO. '\\u' Unicode escape
// \\u 字符串里的Unicode转义字符, 用于直接表示一个Unicode字符
// \\u 后面的数字是十六进制的Unicode Code Point码点表示
public class UnicodeEspace {

    public static void main(String[] args) {
        char unicodeChar1 = '\u0003';
        System.out.println(unicodeChar1);

        char unicodeChar2 = '\u0044';
        System.out.println(unicodeChar2); // D

        char unicodeChar3 = '\u00A9';
        System.out.println(unicodeChar3); // '©'

        String ascii01 = "\u0001 NULL";
        System.out.println(ascii01); //  NULL

        testUnicodeStringColor();
    }

    // 通过码位表示特定的字符串输出颜色
    private static void testUnicodeStringColor() {
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
