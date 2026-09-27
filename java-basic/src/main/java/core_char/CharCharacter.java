package core_char;

// TODO. Char: 一个16 bit的UTF-16 code unit
// 只能表示码点范围U+0000 ~ U+FFFF内字符, 超过范围使用字符串表示
//
// - 每个表示字符都对应一个Code Point编码点(十六进制)
// - 每个表示字符都对应一个整数, 支持相互转换和算术运算
public class CharCharacter {

    private final static int MY_INT = 10;

    public static void main(String[] args) {
        // 判断当个字符的类型
        boolean isDigit = Character.isDigit('D');
        System.out.println(isDigit);

        // 使用常量进行偏移量计算, 隐式转换
        char offsetChar = 'A' + 15;
        char constChar = 'A' + MY_INT;

        char first = 'y';
        char second = 'a';
        int result = first / second;
        System.out.println(result); // 1

        // 使用变量进行偏移计算，显式制转换
        long offset = 10L;
        char convertChar = (char) ('A' + offset);
        char convertChar2 = (char) (65 + offset);
        System.out.println(constChar);
        System.out.println(convertChar2);

        testSpecialChars();
    }

    public static void testSpecialChars() {
        char c = '陈'; // 在单个char字符的表示范围内
        System.out.println(c);
        System.out.println((int) c); // 38472 字符对应十进制的值
        System.out.println(Integer.toHexString((int) c)); // 0x9648 字符对应十六进制码点值

        // U+1F600    一个code point码点, 表示成两个char字符
        //   ↓ UTF-16
        // D83D DE00
        String s = "😀";
        System.out.println(s.length()); // 2
        System.out.println(s.getBytes().length); // 4 对应4个字节的编码长度
    }
}