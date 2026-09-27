package core_string;

public class JavaCharArray {

    public static void main(String[] args) {
        // TODO. 字符数组的字符被初始化成默认值'\u0000'
        char[] chars = new char[2];
        System.out.println(chars); // 字符数组直接以字符串形式输出

        String myStr = "my string";
        char[] charStr = myStr.toCharArray(); // 转换成字符数组

        // 在string字符串拷贝到字符数组中，可以指定要拷贝的偏移量
        char[] input = new char[myStr.length()];
        myStr.getChars(0, myStr.length(), input, 0);

        // 测试字符和字符串的相互转换
        String value = "asa";
        System.out.println((int) value.charAt(0));

        String str1 = String.valueOf((char)3);
        String str2 = String.valueOf((char)34);
        System.out.println(str2);
    }
}
