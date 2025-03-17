package bits_binary.truncation;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

public class StringTruncationApi {

    public static void main(String[] args) {
        // 1 + 3 + 2 非常规字符编码出来的字节数目会大于字符的数量
        String strTruncated = truncateStringByLength("A陈Ã", 4);
        System.out.println(strTruncated);
    }

    // TODO. 算法: 对于字符串编码超出的末尾字节进行截取
    // 使用UTF-8编码格式来编码指定长度的字符串
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

    // TODO. 算法: 依次增加char直到字符串不超过指定的长度
    public static String truncate(String s, int maxLength) {
        StringBuilder output = new StringBuilder(maxLength);
        int outputLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int charLength = 0;
            char c = s.charAt(i);
            if (c <= 0x7f) {
                charLength = 1;
            } else if (c <= 0x7ff) {
                charLength = 2;
            } else if (c <= 0xd7ff) {
                charLength = 3;
            } else if (c <= 0xdbff) {
                charLength = 4;
            } else if (c <= 0xdfff) {
                charLength = 0;
            } else if (c <= 0xffff) {
                charLength = 3;
            }
            if (outputLength + charLength > maxLength) {
                break;
            }
            output.append(c);
            outputLength += charLength;
        }
        return output.toString();
    }
}
