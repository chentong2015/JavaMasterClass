package encoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

// TODO. 字符串中字符数量 != 字符串编码后字节数量
// - 除非字符串中的字符全部时Ascii范围内字符，使用一位byte编码
// - 使用不同的"编码方案"会造成编码后的bytes长度有所不同
public class TestCharsetEncoding {

    public static void main(String[] args) {
        // Charset字符集包含字符编码的格式
        // Returns the default charset of this Java virtual machine.
        Charset.defaultCharset();

        // 必须使用特定的charsetName字符集名称
        Charset charset = Charset.forName("UTF-8");
        Charset defaultCharset = Charset.forName("UTF-16");

        String value1 = "aa"; // 2 chars, 2 bytes
        String value2 = "éé"; // 2 chars, 4 bytes
        String value3 = "陈陈"; // 2 chars, 6 bytes
        String value4 = "ပြည်ထောင်စု သမ္မတ မြန်မာနိုင်ငံတော်"; // 36 chars, 101 bytes
        System.out.println(value4.getBytes(StandardCharsets.UTF_8).length);

        byte[] bytes = "test".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        String str = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str);
    }
}
