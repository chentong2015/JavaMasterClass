package JavaBasic.Char.String;

import java.nio.charset.StandardCharsets;

// 实战场景：
// 由于字符串中含有特殊字符，在进行UTF_8编码时超过最大的字节允许长度，需要按照最大字节长度进行截取
public class JavaStringCutter {

    private static final int MAX_COLUMN_BYTE_LENGTH = 5;

    public static void main(String[] args) {
        String value = "ééé";
        System.out.println(value.length()); // 3个字符
        System.out.println(value.getBytes(StandardCharsets.UTF_8).length); // 6个字节

        final byte[] buffer = value.getBytes(StandardCharsets.UTF_8);
        if (buffer.length > MAX_COLUMN_BYTE_LENGTH) {
            final StringUTF8TruncatedResult truncatedString = truncateUtf8(buffer);
            System.out.println(truncatedString.getTruncatedResult());
            System.out.println(truncatedString.getTruncatedPart());
        }
    }

    // TODO. 通过字节数组来截取字符串, 只取字节数组中指定范围长度的字节
    public static StringUTF8TruncatedResult truncateUtf8(final byte[] utf8bytes) {
        // 不超过最大的允许字节长度，则截取的部分为空
        if (utf8bytes.length <= MAX_COLUMN_BYTE_LENGTH) {
            return new StringUTF8TruncatedResult(
                    new String(utf8bytes, 0, utf8bytes.length, StandardCharsets.UTF_8), "", false);
        }

        // 注意: 最后位置的byte可能是一个char字符的一部分，需要整个字符截取
        int lastIndex = Math.min(utf8bytes.length - 1, MAX_COLUMN_BYTE_LENGTH);
        while (lastIndex > 0 && isContinuation(utf8bytes[lastIndex])) {
            lastIndex--;
        }

        return new StringUTF8TruncatedResult(
                new String(utf8bytes, 0, lastIndex, StandardCharsets.UTF_8),
                new String(utf8bytes, lastIndex, utf8bytes.length - lastIndex, StandardCharsets.UTF_8),
                lastIndex < utf8bytes.length);
    }

    // TODO. 由于是从字节数组中取指定位置的byte字节，如果该字节是连接的则需要将整个char字符进行截断
    //       否则会由于截取不完整而造成结果中出现乱码的情况 éé�
    private static boolean isContinuation(int c) {
        return (c & 0xc0) == 0x80;
    }

    // 面向对象设计：将截取完的字符串作为Result结果对象
    private static class StringUTF8TruncatedResult {
        private final String result;
        private final String truncatedPart;
        private final boolean isOverflow;

        public StringUTF8TruncatedResult(String result, String truncatedPart, boolean isOverflow) {
            this.result = result;
            this.truncatedPart = truncatedPart;
            this.isOverflow = isOverflow;
        }

        public String getTruncatedResult() {
            return result;
        }

        public String getTruncatedPart() {
            return truncatedPart;
        }

        // 判断截取完的字符串是否溢出: 仍然超过最大允许字节长度
        public boolean isOverflowOrError() {
            return isOverflow;
        }

        // 判断是否截取的字符串是有意义的，如果只是截取字符串末尾的空格，则直接调用string..trim()
        public boolean isTruncatedPartMeaningful() {
            return !truncatedPart.trim().isEmpty();
        }
    }
}
