package JavaRegularExpressions.string;

// TODO. 正确使用含对正则表达式参数的String原生API
// 当参数不是正则表示式时，不推荐使用含正则的API方法，会对性能造成影响
public class PatternMatcherString {

    public static void main(String[] args) {
        String value = "this is a test line";

        // 非正则形式，从头到尾替换所有匹配的子字符串
        value = value.replace("this", "that");

        // 使用正则表达式子能够匹配并替换多种子字符串
        value = value.replace("i*.", "as");
        value = value.replaceFirst("i*.", "as");

        boolean isMatch = value.matches(".*");

        value = value.replaceAll("[ ]?,[ ]?", ",");

        String[] items = value.split("[ ]?,[ ]?");
    }
}
