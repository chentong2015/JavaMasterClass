package string_truncation;

import java.nio.charset.StandardCharsets;

// 实战场景: 由于字符串中含有特殊字符，UTF_8编码时超过最大的字节允许长度，需要按照最大字节长度截取
public class StringTruncation {

    private static final int MAX_COLUMN_BYTE_LENGTH = 5;

    public static void main(String[] args) {
        String value = "ééé";
        System.out.println(value.length()); // 3个字符
        System.out.println(value.getBytes(StandardCharsets.UTF_8).length); // 6个字节

        final byte[] buffer = value.getBytes(StandardCharsets.UTF_8);
        if (buffer.length > MAX_COLUMN_BYTE_LENGTH) {
            final StringTruncatedUTF8Result truncatedString = truncateUtf8(buffer);
            System.out.println(truncatedString.getTruncatedResult());
            System.out.println(truncatedString.getTruncatedPart());
        }
    }

    // TODO. 通过字节数组来截取字符串, 只取字节数组中指定范围长度的字节
    public static StringTruncatedUTF8Result truncateUtf8(final byte[] utf8bytes) {
        // 不超过最大的允许字节长度，则截取的部分为空
        if (utf8bytes.length <= MAX_COLUMN_BYTE_LENGTH) {
            String result = new String(utf8bytes, 0, utf8bytes.length, StandardCharsets.UTF_8);
            return new StringTruncatedUTF8Result(result, "", false);
        }

        // 注意: 最后位置的byte可能是一个char字符的一部分，需要整个字符截取
        int lastIndex = MAX_COLUMN_BYTE_LENGTH;
        while (lastIndex > 0 && isContinuation(utf8bytes[lastIndex])) {
            lastIndex--;
        }

        return new StringTruncatedUTF8Result(
                new String(utf8bytes, 0, lastIndex, StandardCharsets.UTF_8),
                new String(utf8bytes, lastIndex, utf8bytes.length - lastIndex, StandardCharsets.UTF_8),
                lastIndex < MAX_COLUMN_BYTE_LENGTH);
    }

    // TODO. 从字节数组中取指定位置的byte字节
    //  如果该字节是"连接"的则需要将整个char字符进行截断
    //  否则会由于截取不完整而造成结果中出现乱码的情况 éé�
    private static boolean isContinuation(int c) {
        return (c & 0xc0) == 0x80;
    }
}
