package codec_algo;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// TODO. Base64 Encoding ([A-Z, a-z, 0-9, ., -])
// Base64是一种字符编码方式，即使用64种字符编码原始字符，生成字符组合结果
// 1 letter: 64 results
// 2 letters: 64*64 results
// 3 letters: 64*64*64 results
// 每增加一个字符，将增加64倍的结果组合可能
public class JavaBase64Encoder {

    // TODO. 字符串和字节转义时采用统一的UTF-8编码格式
    public static void main(String[] args) {
        // bytes = [49, 49, 48]
        String str = "110";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        // bytesEncoded = [77, 84, 69, 119] -> MTEw 根据编码的Byte位，计算77%64=13=E对应字符
        byte[] bytesEncoded = Base64.getEncoder().encode(bytes);
        String strEncoded = new String(bytesEncoded);
        System.out.println(strEncoded);

        // bytesDecoded = [49, 49, 48] -> 110 根据编码的Bytes结果，解码原始的bytes，恢复字符串
        byte[] bytesDecoded = Base64.getDecoder().decode(strEncoded);
        String strDecoded = new String(bytesDecoded, StandardCharsets.UTF_8);
        System.out.println(strDecoded);
    }
}
