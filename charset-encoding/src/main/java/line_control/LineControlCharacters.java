package line_control;

// 控制字符(换行符), 不同系统的行分隔符不同
// control characters => 0x0A (10 decimal)
// CR = Carriage Return & LF = Line Feed
// - Windows: '\r\n'
// - Mac (OS 9-): '\r'
// - Mac (OS 10+): '\n'
// - Unix/Linux: '\n'
public class LineControlCharacters {

    // 获取OS系统指定的行分隔符, 跨OS平台
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
        System.out.println("a" + LINE_SEPARATOR + "b");

        String value = "aa\r\nbb";
        System.out.println(value);
        System.out.printf(value.replaceAll(LINE_SEPARATOR, " "));

        String value2 = "cc\r\ndd";
        if (value2.contains(LINE_SEPARATOR)) {
            System.out.println(value2);
        }
    }

    // 必须添加转译符号将换行符号以字符串显示出来
    public String escapeControlCharacters(String value) {
        if (value.contains(LINE_SEPARATOR)) {
            switch (LINE_SEPARATOR) {
                case "\r\n":
                    return value.replaceAll(LINE_SEPARATOR, " \\\\r\\\\n ");
                case "\n":
                    return value.replaceAll(LINE_SEPARATOR, " \\\\n ");
                case "\r":
                    return value.replaceAll(LINE_SEPARATOR, " \\\\r ");
                default:
                    return value;
            }
        }
        return value;
    }

    // 用于测试目的，兼容不同平台的实际换行字符
    static String getControlCharactersAsString() {
        switch (LINE_SEPARATOR) {
            case "\r\n":
                return " \\r\\n "; // For windows
            case "\n":
                return " \\n ";  // For Unix/Linux & Mac (OS 10+)
            case "\r":
                return " \\r ";  // For Mac (OS 9-)
            default:
                return getLineSeparator();
        }
    }

    public static String getLineSeparator() {
        return LINE_SEPARATOR;
    }
}
