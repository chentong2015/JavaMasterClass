package JavaBasic.Char;

import java.text.Normalizer;

public class JavaCharNormalizer {

    public static void main(String[] args) {
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
}
