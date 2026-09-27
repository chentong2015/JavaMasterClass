package core_char;

import java.text.Normalizer;

// 测试码位的表示和操作
public class AsciiChar {

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


    public static void mainll(String[] args) {
        // ea 去掉所有非ASCII字符之后剩余两个字符
        System.out.println(normalizeToAscii("éà"));

        // ́  格式之后变成4个长度的字符串
        System.out.println(Normalizer.normalize("éà", Normalizer.Form.NFKD).charAt(1));
    }

    // Java提供标准化器将Unicode格式化成ASCII码值
    // Transform UTF-16 string to an ASCII only string.
    // For glyphs composed as ASCII + accent -> return ASCII base ('à' -> 'a' + '`')
    // For glyphs composed as several ASCII -> return sequence of ASCII ('ffi' -> 'f' + 'f' + 'i')
    // For other ones, remove thms.
    public static String normalizeToAscii(final String value) {
        // \p{ASCII} -> [\x00-\x7F] 表示所有ASCII字符集
        // ^\p{ASCII} -> 表示所有非ASCII字符集
        String regex = "[^\\p{ASCII}]";

        // 将所有的非ASCII字符进行抹去
        return Normalizer.normalize(value, Normalizer.Form.NFKD).replaceAll(regex, "");
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
