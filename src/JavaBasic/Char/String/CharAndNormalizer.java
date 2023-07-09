package JavaBasic.Char.String;

import java.text.Normalizer;

// 代码中的所有char字符均会被转换成16bit位的Unicode码值: 0000-0FFF(可编65536字符)
// 代码中只有256个字符为8bit位的ASCII码值

// Unicode Technical:
// https://www.unicode.org/reports/
// https://symbl.cc/en/unicode/blocks/
public class CharAndNormalizer {

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

        String myString = "\u00A9 2019"; // 直接在字符串中使用 unicode码值
    }

    /**
     * Java提供标准化器将Unicode格式化成ASCII码值
     * Transform UTF-16 string to an ASCII only string.
     * For glyphs composed as ASCII + accent -> return ASCII base ('à' -> 'a' + '`')
     * For glyphs composed as several ASCII -> return sequence of ASCII ('ffi' -> 'f' + 'f' + 'i')
     * For other ones, remove thms.
     *
     * @param value the string to normalize
     * @return an ASCII only string
     */
    public static String normalizeToAscii(final String value) {
        // \p{ASCII} -> [\x00-\x7F] 表示所有的ASCII字符集
        String regex = "[^\\p{ASCII}]";

        // 将所有的非ASCII字符进行抹去
        return Normalizer.normalize(value, Normalizer.Form.NFKD).replaceAll(regex, "");
    }

    public static void main(String[] args) {
        System.out.println("éà".charAt(1));  // à 包含两个unicode的字符
        System.out.println(Normalizer.normalize("éà", Normalizer.Form.NFKD).charAt(1)); // ́  格式之后变成4个长度的字符串
        System.out.println(normalizeToAscii("éà")); // ea 去掉所有非ASCII字符之后剩余两个字符
    }
}
