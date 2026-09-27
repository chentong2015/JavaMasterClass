package core_char;

import java.nio.charset.StandardCharsets;

//
// // Char字符对应到Unicode码值，
//
//String字符串本质上是一个16 bits unicode字符码值的数组
//String对应Unicode码值，表示UTF-16编码方案 ???
//
//
// TODO: Code Point = UFT-8编码划分点
// 由于UFT-8编码格式会将单个字符编码成不同的字节长度(1-4个字节)
// 对于特殊的字符需要额外的空间来存储，因为编码字节的数目大于字符的数目
public class CodePoint {











    // TODO. 判断编码后每个字节位置是否连续，非连续位置才能截取，避免截断后会乱码 !!
    public static void main(String[] args) {
        char c = '陈';
        System.out.println(c);
        System.out.println((int) c); // 38472 字符对应十进制的值
        System.out.println(Integer.toHexString((int) c)); // 0x9648 字符对应十六进制码点值



        // 1 + 3 + 2 + 2 不同的字符编码的字节数不同
        String value = "*陈Ã©";
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        int byteLength = value.getBytes(StandardCharsets.UTF_8).length;
        for (int i = byteLength - 1; i >= 0; i--) {
            System.out.println(isContinuation(bytes[i]));
        }

        int lastIndex = 2;
        while (lastIndex > 0 && isContinuation(bytes[lastIndex])) {
            lastIndex--;
        }
        String result = new String(bytes, 0, lastIndex, StandardCharsets.UTF_8);
        System.out.println(result);
    }

    // TODO. 编码点判断: 判断是否是code point的边界
    //  找到最后一个完整的UTF-8字符边界 !!
    private static boolean isContinuation(int c) {
        return (c & 0xc0) == 0x80;
    }
}
