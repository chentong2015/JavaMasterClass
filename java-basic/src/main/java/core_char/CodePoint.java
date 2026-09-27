package core_char;

// TODO. 所有字符都有对应的Unicode Code Point码点
// Unicode码点空间支持范围U+000000 ~ U+10FFFF
// Unicode码点最多支持字符0 ~ 1,114,111数量
//
// - 某些码点已经划分给特殊的字符使用
// - 某些特殊字符可能需要不只一个码点来表示 !!
public class CodePoint {

    public static void main(String[] args) {
        int codePoint = Character.codePointAt("abc", 0);
        System.out.println(codePoint); // 97
        System.out.println(Integer.toHexString(codePoint)); // Ox61 -> UTF8 1 bytes

        int codePoint2 = Character.codePointAt("陈", 0);
        System.out.println(codePoint2); // 38472
        System.out.println(Integer.toHexString(codePoint2)); // Ox9648 -> UTF8 3 bytes
    }
}
