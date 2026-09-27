package core_char;

// TODO. Char 字符: 构成字符串的单元
// - 每个字符都对应一个Code Point编码点(十六进制)
// - 每个字符都对应一个整数, 支持相互转换和算术运算
public class JavaCharacter {

    private final static int MY_INT = 10;

    // Char字符可以转换int值比较计算
    // Int值可转换成对于的Char值，再转换成字符串
    public static void main(String[] args) {
        String s = "😀";
        // java必须使用两个char才能表示这个“单个字符”，即时它只有一个code point码点
        // 它的U+1F600码点数在UTF-16 code unit (U+0000 ~ U+FFFF) 编码单元之外
        System.out.println(s.length()); // 2
        System.out.println(s.getBytes().length); // 4 对应4个字节的编码长度


        char first = 'y';
        char second = 'a';
        int result = first / second;
        System.out.println(result); // 1



        char myChar = 'D';

        // 使用常量进行偏移量计算, 隐式转换
        char offsetChar = 'A' + 15;
        char constChar = 'A' + MY_INT;

        // 使用变量进行偏移计算，显式制转换
        long offset = 10L;
        char convertChar = (char) ('A' + offset);
        char convertChar2 = (char) (65 + offset);
        System.out.println(constChar);
        System.out.println(convertChar2);


        String value = "asa";
        System.out.println((int) value.charAt(0));

        String str1 = String.valueOf((char)3);
        String str2 = String.valueOf((char)34);
        System.out.println(str2);

        // TODO. Character API判断单个字符的类型
        String str = "1test";
        boolean isDigit = Character.isDigit(str.charAt(0));
        System.out.println(isDigit);

        int codePoint = Character.codePointAt("😀abc", 0);
        System.out.println(codePoint); // 128512
        System.out.println(Integer.toHexString(codePoint)); // Ox1f600
    }
}
