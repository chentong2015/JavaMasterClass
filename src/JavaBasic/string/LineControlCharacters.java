package JavaBasic.string;

// 控制字符(换行符), 不同系统的行分隔符不同
// control characters => 0x0A (10 decimal)
// CR = Carriage Return & LF = Line Feed
// - Windows: '\r\n'
// - Mac (OS 9-): '\r'
// - Mac (OS 10+): '\n'
// - Unix/Linux: '\n'
public class LineControlCharacters {

    public static void main(String[] args) {
        // 获取OS系统指定的行分隔符
        String lineSeparator = System.getProperty("line.separator");
        System.out.println("a" + lineSeparator + "b");

        String value = "aa\r\nbb";
        System.out.println(value);
        System.out.printf(value.replaceAll(lineSeparator, " "));

        String value2 = "cc\r\ndd";
        if (value2.contains(lineSeparator)) {
            System.out.println(value2);
        }

    }
}
