package JavaBasic.CharEncoding;

import java.nio.charset.StandardCharsets;

// 实战场景：UFT-8编码的划分点成为Code Point
// 由于UFT-8编码格式会将单个字符编码成不同的字节长度(1-4个字节)
// 对于特殊的字符需要额外的空间来存储，因为编码字节的数目大于字符的数目
public class CodePointEncoding {

    public static void main(String[] args) {
        // 3 + 3 + 3 对应unicode码值，对应不同编码数目
        String value = "A陈\u0802";
        System.out.println(value.length());
        int byteLength = value.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(byteLength);

        testCodePoint();
    }

    // 逐个字符的判断编码字节的长度，并截取
    public static void testCodePoint() {
        // 2 + 3 + 2 + 2 不同的字符编码的字节数不同
        String value = "*陈Ã©";
        int charLength = value.length();
        int byteLength = value.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(byteLength);

        int index = 1;
        String result = value;
        while (byteLength > 2) {
            result = value.substring(0, charLength - index);
            System.out.println(result);

            byteLength = result.getBytes(StandardCharsets.UTF_8).length;
            index++;
        }
        // 最后剩余的字符编码的字节数为1或2
        System.out.println(result);
    }
}
