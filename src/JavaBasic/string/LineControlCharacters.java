package JavaBasic.string;

// 控制字符(换行符), 不同系统的行分隔符不同
// control characters => 0x0A (10 decimal)
// CR = Carriage Return & LF = Line Feed
// - Windows: '\r\n'
// - Mac (OS 9-): '\r'
// - Mac (OS 10+): '\n'
// - Unix/Linux: '\n'
public class LineControlCharacters {

    // 获取OS系统指定的行分隔符, 跨OS平台
    private static String lineSeparator = System.getProperty("line.separator");

    public static void main(String[] args) {
        System.out.println("a" + lineSeparator + "b");

        String value = "aa\r\nbb";
        System.out.println(value);
        System.out.printf(value.replaceAll(lineSeparator, " "));

        String value2 = "cc\r\ndd";
        if (value2.contains(lineSeparator)) {
            System.out.println(value2);
        }
    }

    // 如果要将换行符号以字符串的方式显示出来，则必须添加转译符号
    public String escapeControlCharacters(String value) {
        if (value.contains(lineSeparator)) {
            switch (lineSeparator) {
                case "\r\n":
                    // For windows
                    return value.replaceAll(lineSeparator, " \\\\r\\\\n ");
                case "\n":
                    // For Unix/Linux & Mac (OS 10+)
                    return value.replaceAll(lineSeparator, " \\\\n ");
                case "\r":
                    // For Mac (OS 9-)
                    return value.replaceAll(lineSeparator, " \\\\r ");
                default:
                    return value;
            }
        }
        return value;
    }
}
