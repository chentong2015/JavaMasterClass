package core_char;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

// TODO. 相同的Code Point值采用不同的编码方案会得到不同的byte结果
// US-ASCII   只能编码7位的字符集(0~127), 最高位为0值
// ISO-8859-1 只能编码8位的字符集(0~256), ASCII的扩展
// UTF-8      能表示完整的Unicode码点范围，以8 bits为单位编码成1~4个字节(节省空间)
// UTF-16     能表示完整的Unicode码点范围，以16 bits为单位编码成2或4个字节
public class CodePointEncoding {

    public static void main(String[] args) {
        // Returns the default charset of this Java virtual machine.
        Charset defaultCharset = Charset.defaultCharset();

        // 自定义使用的Charset名称
        Charset charset8 = Charset.forName("UTF-8");
        Charset default16 = Charset.forName("UTF-16");

        byte[] bytesAscii = "陈".getBytes(StandardCharsets.US_ASCII);
        System.out.println(bytesAscii.length);      // 1 一个字节
        System.out.println(new String(bytesAscii)); // 编码失败，解码成?字符

        byte[] bytesIso = "陈".getBytes(StandardCharsets.ISO_8859_1);
        System.out.println(bytesIso.length);      // 1 一个字节
        System.out.println(new String(bytesIso)); // 编码失败，解码成?字符

        // TODO. 默认使用UTF-8 Charset编码方案
        byte[] bytesUtf8 = "陈".getBytes();
        System.out.println(bytesUtf8.length);      // 3 三个字节
        System.out.println(new String(bytesUtf8)); // 解码正确

        // TODO. 编码方案和解码方案必须一致, 否则乱码
        byte[] bytes16 = "陈".getBytes(StandardCharsets.UTF_16);
        System.out.println(bytes16.length);         // 4 必须扩展到四个字节存储
        System.out.println(new String(bytes16, StandardCharsets.UTF_16)); // 解码正确

        testNormalizeToAscii();
    }

    // Transform UTF-16 string to an ASCII only string.
    // For glyphs composed as ASCII + accent -> return ASCII base ('à' -> 'a' + '`')
    // For glyphs composed as several ASCII -> return sequence of ASCII ('ffi' -> 'f' + 'f' + 'i')
    // For other ones, remove thms.
    public static void testNormalizeToAscii() {
        String regex = "[^\\p{ASCII}]"; // 匹配所有非ASCII字符集
        String result1 = Normalizer.normalize("éà", Normalizer.Form.NFKD).replaceAll(regex, "");
        System.out.println(result1);    // ea 格式化成ASCII码值内的字符
    }

    // 根据UTF-8编码的字节来截断字符串, 避免乱码
    public static void testUtf8Bytes() {
        String value = "*陈Ã©";
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes.length);

        for (int i = bytes.length - 1; i >= 0; i--) {
            System.out.println(isContinuation(bytes[i]));
        }
        System.out.println(new String(bytes, 0, 4));
    }

    // TODO. 判断一个字节是不是UTF-8的continuation byte后续字节
    private static boolean isContinuation(int c) {
        return (c & 0xc0) == 0x80;
    }
}
