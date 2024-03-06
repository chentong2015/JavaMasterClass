package JavaBasic.CharEncoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

public class CharsetEncoder {

    // TODO. Java Charset字符集包含字符编码的所有格式
    // 必须使用特定的charsetName字符集名称
    // US-ASCII     Seven-bit ASCII, the Basic Latin block of the Unicode character set
    // ISO-8859-1   ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
    // UTF-8        Eight-bit UCS Transformation Format
    // UTF-16BE     Sixteen-bit UCS Transformation Format, big-endian byte-order
    // UTF-16LE     Sixteen-bit UCS Transformation Format, little-endian byte-order
    // UTF-16       Sixteen-bit UCS Transformation Format, byte-order identified by an optional byte-order mark
    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");
        Charset defaultCharset = Charset.forName("UTF-16");

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
        // Ignore an incomplete character at end 关于格式错误的输入
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
