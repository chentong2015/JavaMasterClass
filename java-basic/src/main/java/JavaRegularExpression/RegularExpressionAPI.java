package JavaRegularExpression;

public class RegularExpressionAPI {

    public static void main(String[] args) {
        // TODO. 非正则形式: 替换所有匹配的子字符串
        String value = "this test line this test";
        String result = value.replace("this", "OK");

        // TODO. 正则表达式形式:
        // 判断正则表达式是否匹配字符串(全部字符)
        boolean isMatch = value.matches(".*");

        // 只替换第一个匹配的子字符串
        result = value.replaceFirst("s ", "as");

        // 替换所有能够匹配的子字符串
        result = value.replaceAll("t.*t ", "as");
        result = value.replaceAll("[ ]?,", ",");
    }
}
