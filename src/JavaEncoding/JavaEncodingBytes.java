package JavaEncoding;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// TODO. UTF-8字符编码格式, code-point字符编码的点 !!
// UFT-8会将部分字符编码成1个字节， 部分编码成2个字节，3个字节，4个字节
public class JavaEncodingBytes {

    // 按照UTF-8编码方案进行编码后，得到的字节数目
    public void testEncodingBytes() {
        String value1 = "aa"; // 2 chars, 2 bytes
        String value2 = "éé"; // 2 chars, 4 bytes
        String value3 = "陈陈";
        System.out.println(value3.length()); // 2 chars
        System.out.println(value3.getBytes(StandardCharsets.UTF_8).length); // 6 bytes
    }

    // 按照UTF-8的code-point编码点来截取子字符串
    public static void main(String[] args) {
        String value = "陈陈";
        int byteLength = value.getBytes(StandardCharsets.UTF_8).length;
        String result = value;
        if (byteLength > 2) {
            int index = 1;
            while (byteLength > 2) {
                result = value.substring(0, value.length() - index);
                byteLength = result.getBytes(StandardCharsets.UTF_8).length;
                index++;
            }
        }
        System.out.println(result);
    }

    // 字符和字节之间的编码和解码
    public static void testStringBytes() {
        // String value = "aaé";
        String value = "aa@";
        byte[] originalBytes = value.getBytes(StandardCharsets.UTF_8);
        System.out.println(originalBytes.length);

        // 从字节数组中复制指定字节长度
        byte[] resultBytes = Arrays.copyOf(originalBytes, 2);
        String result = new String(resultBytes);
        System.out.println(result);

        // 如果最后截取的字节不完整(不能构成一个完整的字符)，则会出现位置乱码符号
        if (result.contains("�")) {
            System.out.println("find error");
        }
    }
}
