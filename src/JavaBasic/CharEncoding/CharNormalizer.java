package JavaBasic.CharEncoding;

import java.text.Normalizer;

public class CharNormalizer {

    private final static int MY_INT = 10;

    public static void main(String[] args) {
        char myChar = 'D';
        char offsetChar = 'A' + 15;               // 1. 使用常量进行偏移量计算, 隐式转换 !!
        char constChar = 'A' + MY_INT;

        int offset = 10;
        char convertChar = (char) ('A' + offset); // 2. 使用变量进行偏移计算，显示强制转换 !!
        char convertChar2 = (char) (65 + offset);
        char myUnicodeChar = '\u0044';            // \\u 表示码位的表示
        char myCopyRightUnicodeChar = '\u00A9';   // 0x00A9 十六进制写法

        // à 包含两个unicode的字符
        System.out.println("éà".charAt(1));

        // ́  格式之后变成4个长度的字符串
        System.out.println(Normalizer.normalize("éà", Normalizer.Form.NFKD).charAt(1));

        // ea 去掉所有非ASCII字符之后剩余两个字符
        System.out.println(normalizeToAscii("éà"));
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
        return Normalizer.normalize(value, Normalizer.Form.NFKD)
                .replaceAll(regex, "");
    }
}
