package JavaEncoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

// 使用指定的编码格式来将字符串截取成最大的字节长度
public class JavaCharsetEncoder {

    public static String truncateStringByLength(String value, int maxLength) {
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
