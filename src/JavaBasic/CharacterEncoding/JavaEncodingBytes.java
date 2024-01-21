package JavaBasic.CharacterEncoding;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// TODO. UTF-8字符编码格式, code-point字符编码的点
// UFT-8会将部分字符编码成1个字节， 部分编码成2个字节，3个字节，4个字节
public class JavaEncodingBytes {

    // 按照UTF-8的code-point编码点来截取子字符串
    public static void main(String[] args) {
        // String value = "陈\u0802"; 使用unicode码值来对应指定的编码字节数目
        String value = "陈Ã©"; // 3 + 2 + 2
        System.out.println(value.length());
        int byteLength = value.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(byteLength);
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
        // Charset.defaultCharset()使用默认的字节"解码方案"
        String result = new String(resultBytes);
        System.out.println(result);

        // 如果最后截取的字节不完整(不能构成一个完整的字符)，则会出现位置乱码符号
        if (result.contains("�")) {
            System.out.println("find error");
        }
    }

    // 使用特定的"编码方案"生成字节，"解码方案"转成字符串
    private void convertByteBufferToString() {
        String value1 = "aa"; // 2 chars, 2 bytes
        String value2 = "éé"; // 2 chars, 4 bytes
        String value3 = "陈陈";
        System.out.println(value3.length()); // 2 chars
        System.out.println(value3.getBytes(StandardCharsets.UTF_8).length); // 6 bytes

        byte[] bytes = "test".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        String str = StandardCharsets.UTF_8.decode(buffer).toString();
    }
}
