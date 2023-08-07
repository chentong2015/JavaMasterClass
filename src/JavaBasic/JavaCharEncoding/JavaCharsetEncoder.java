package JavaBasic.JavaCharEncoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

public class JavaCharsetEncoder {

    // TODO. Java Charset字符集包含字符编码的所有格式
    // US-ASCII     Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set
    // ISO-8859-1   ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
    // UTF-8        Eight-bit UCS Transformation Format
    // UTF-16BE     Sixteen-bit UCS Transformation Format, big-endian byte-order
    // UTF-16LE     Sixteen-bit UCS Transformation Format, little-endian byte-order
    // UTF-16       Sixteen-bit UCS Transformation Format, byte-order identified by an optional byte-order mark
    public void testCharSet() {
        // 必须提供有限的字符集名称
        Charset charset = Charset.forName("UTF-8");
        Charset defaultCharset = Charset.forName("UTF-16");
    }

    // 使用指定的编码格式来将字符串截取成最大的字节长度
    public String truncateStringByLength(String value, int maxLength) {
        CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        CharBuffer input = CharBuffer.wrap(value);
        ByteBuffer output = ByteBuffer.allocate(maxLength);

        // Ignore an incomplete character at end
        encoder.onMalformedInput(CodingErrorAction.IGNORE);

        // Encodes as many characters as possible from the given input buffer
        // writing the results to the given output buffer.
        encoder.encode(input, output, true);
        encoder.flush(output);

        // The charset to be used to decode the bytes 构建字符串，提供解码方案
        return new String(output.array(), 0, output.position(), StandardCharsets.UTF_8);
    }
}
