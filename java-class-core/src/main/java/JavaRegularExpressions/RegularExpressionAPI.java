package JavaRegularExpressions;

public class RegularExpressionAPI {

    public static void main(String[] args) {
        // TODO. 非正则形式: 替换所有匹配的子字符串
        String value = "this test line this test";
        String result = value.replace("this", "OK");
        System.out.println(result);

        // TODO. 正则表达式形式: 使用正则参数的API方法 => 对性能有影响
        // 判断正则是否匹配字符串, 必须全部字符都匹配 !!
        boolean isMatch = value.matches(".*");
        System.out.println(isMatch);

        // 只替换第一个匹配的子字符串
        result = value.replaceFirst("s ", "as");
        System.out.println(result);

        // 替换所有能够匹配的子字符串
        result = value.replaceAll("t.*t ", "as");
        System.out.println(result);
        result = value.replaceAll("[ ]?,", ",");
        System.out.println("---------");
    }

    // TODO. 正则表达式形式: Split分隔字符串
    public static void testRegExSplit() {
        // 如果分隔点后没有数据，则只保留开头子字符串
        String info = "[item]";
        String[] infos = info.split("item]");
        System.out.println(infos.length); // "["

        String value = "this is a test";
        String[] items = value.split("is"); // "th", " ", " a test"

        // 如果没有找到分割字符，则结果为原始字符串
        String valueStr = "this is a test";
        String[] itemsStr = valueStr.split(":");
        System.out.println(itemsStr.length); // "this is a test"
    }
}
