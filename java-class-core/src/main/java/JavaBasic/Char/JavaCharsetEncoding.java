package JavaBasic.Char;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

// TODO. Java Charset字符集包含字符编码的格式
public class JavaCharsetEncoding {

    public static void main(String[] args) {
        // Returns the default charset of this Java virtual machine.
        Charset.defaultCharset();

        // 必须使用特定的charsetName字符集名称
        Charset charset = Charset.forName("UTF-8");
        Charset defaultCharset = Charset.forName("UTF-16");

        // TODO. 使用特定的"编码方案"生成字节，"解码方案"转成字符串
        String value1 = "aa"; // 2 chars, 2 bytes
        String value2 = "éé"; // 2 chars, 4 bytes
        String value3 = "陈陈"; // 2 chars, 6 bytes
        System.out.println(value3.length());
        System.out.println(value3.getBytes(StandardCharsets.UTF_8).length);

        byte[] bytes = "test".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        String str = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str);

        // 1 + 3 + 2 非常规字符编码出来的字节数目会大于字符的数量
        String strTruncated = truncateStringByLength("A陈Ã", 4);
        System.out.println(strTruncated);
    }

    // 使用UTF-8编码格式来编码指定长度的字符串
    // 对于字符串编码超出的末尾字节进行截取
    public static String truncateStringByLength(String value, int maxByteLength) {
        CharBuffer input = CharBuffer.wrap(value);
        ByteBuffer output = ByteBuffer.allocate(maxByteLength);

        java.nio.charset.CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        encoder.onMalformedInput(CodingErrorAction.IGNORE);
        // Encodes as many characters as possible from the given input buffer to output buffer
        encoder.encode(input, output, true);
        // Flushes this encoder
        encoder.flush(output);

        // TODO. 使用ByteBuffer来构建字符串
        byte[] bytes = output.array();
        int bytesLength = output.position();
        return new String(bytes, 0, bytesLength, StandardCharsets.UTF_8);
    }
}
