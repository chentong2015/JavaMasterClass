package core_char;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// TODO. 字符串中字符数量 != 字符串编码后字节数量
// - 除非字符串中的字符全部时Ascii范围内字符，使用一位byte编码
// - 使用不同的"编码方案"会造成编码后的bytes长度有所不同
public class CharsetEncoding {

    // Charset字符集包含字符编码的格式
    public static void main(String[] args) {

        String s = "😀";
        System.out.println(s.length());
        System.out.println(s.getBytes(StandardCharsets.UTF_8).length);

        // Returns the default charset of this Java virtual machine.
        Charset.defaultCharset();

        // 自定义使用的Charset名称
        Charset charset = Charset.forName("UTF-8");
        Charset defaultCharset = Charset.forName("UTF-16");
        Charset standardCharset = StandardCharsets.UTF_16;

        String value1 = "aa"; // 2 chars, 2 bytes
        String value2 = "éé"; // 2 chars, 4 bytes
        String value3 = "陈陈"; // 2 chars, 6 bytes
        String value4 = "ပြည်ထောင်စု သမ္မတ မြန်မာနိုင်ငံတော်"; // 36 chars, 101 bytes
        System.out.println(value4.getBytes(StandardCharsets.UTF_8).length);

        byte[] bytes = "test".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        String str = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str);

        testEncodings();
    }

    // 测试不同编码方案的效果
    public static void testEncodings() {
        // 默认使用UTF-8编码方案
        // 一个字符编码成3个字节的长度: 11101001 10011001 10001000
        byte[] bytes = "陈".getBytes(); // [-23, -103, -120]
        for (byte b : bytes) {
            System.out.println(Integer.toBinaryString(b));
        }
        System.out.println(new String(bytes)); // 编码正确，能够被解析成原本字符


        byte[] bytes1 = "陈".getBytes(StandardCharsets.UTF_16); // 16位不够编码3个字节的字符
        System.out.println(bytes1[0]); // [-2, -1, -106, 72]
        System.out.println(new String(bytes1)); // 编码失败，解码成乱吗

        byte[] bytes2 = "陈".getBytes(StandardCharsets.US_ASCII);
        System.out.println(bytes2[0]); //
        System.out.println(new String(bytes2)); // 编码失败，解码成?字符


        byte[] bytes3 = "陈".getBytes(StandardCharsets.ISO_8859_1);
        System.out.println(new String(bytes3)); // 编码失败，解码成?字符
    }

    public void test(String str) {
        // 将字符串按照指定的方案"解码"成byte数组，然后再按照指定的方案"编码"成String
        byte[] oldBytes = str.getBytes(StandardCharsets.US_ASCII);
        String newStr = new String(oldBytes, StandardCharsets.UTF_8);

    }
}
