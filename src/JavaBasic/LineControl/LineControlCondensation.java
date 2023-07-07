package JavaBasic.LineControl;

import java.io.IOException;
import java.io.Writer;

// 换行符缩合:
// 对于包含换行符号的字符串，需要将对于的换行符号转成字符，并将字符串缩合成一行
// 由于Java提供的String.replaceAll()方法在某些场景比较耗时，需要从字符角度来处理
public class LineControlCondensation {

    public static void writeQuoted(final String value, final Writer out) throws IOException {
        out.write('"');
        int start = 0;
        int current = 0;
        char[] input = new char[value.length()];
        value.getChars(0, value.length(), input, 0);

        for (; current < value.length(); ) {
            int cp = (int) input[current] & 0xFFFF;
            // Search for character needing escaping
            if (cp < 0x20 || cp == 0x22 || cp == 0x5C || cp >= 0x80) {
                out.write(value, start, current - start);
                switch (cp) {
                    case '"' -> out.write("\\\"");
                    case '\\' -> out.write("\\\\");
                    case '\b' -> out.write("\\b");
                    case '\f' -> out.write("\\f");
                    case '\n' -> out.write("\\n");
                    case '\r' -> out.write("\\r");
                    case '\t' -> out.write("\\t");
                    case '\0' -> out.write("\\0");
                    default -> {
                        if (isUCSScalar(cp)) {
                            out.write("\\u");
                        } else {
                            out.write("\\x");
                        }
                        String hex = Integer.toHexString(cp);
                        for (int i = hex.length(); i < 4; ++i) {
                            out.write('0');
                        }
                        out.write(hex);
                    }
                }
                start = current + 1;
            }
            ++current;
        }
        // Write remainings chars.
        if (current != start) {
            out.write(value, start, current - start);
        }
        out.write('"');
    }

    static boolean isUCSScalar(int cp) {
        // valid range is [0, 10FFFF].
        // scalar is anything that is not a surrogate.
        return !isSurrogate(cp);
    }

    static boolean isSurrogate(int cp) {
        // surrogate range is [D800, DFFF].
        return cp >= 0xD800 && cp <= 0xDFFF;
    }
}
