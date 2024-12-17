package encoding.utf8;

import java.nio.charset.StandardCharsets;

// 实战场景:
// UFT-8编码的划分点成为Code Point
// 由于UFT-8编码格式会将单个字符编码成不同的字节长度(1-4个字节)
// 对于特殊的字符需要额外的空间来存储，因为编码字节的数目大于字符的数目
public class Utf8CodePoints {

    public static void main(String[] args) {
        // 1 + 3 + 2 + 2 不同的字符编码的字节数不同
        String value = "*陈Ã©";

        int charLength = value.length();
        System.out.println(charLength);

        int byteLength = value.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(byteLength);

        // TODO. 逐个遍历并截取字符，等效在编码点截取
        int index = 1;
        String result = value;
        while (byteLength > 2) {
            result = value.substring(0, charLength - index);
            System.out.println(result);

            byteLength = result.getBytes(StandardCharsets.UTF_8).length;
            System.out.println(byteLength);
            index++;
        }

        // 最后剩余的字符编码的字节数为1或2
        System.out.println(result);
    }
}
