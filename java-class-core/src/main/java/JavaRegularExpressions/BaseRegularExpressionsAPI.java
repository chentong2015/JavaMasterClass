package JavaRegularExpressions;

// TODO. 当参数不是正则表示式时
//  - 不推荐使用含正则的API方法，会对性能造成影响
//  - 直接使用.replace()来替换所有匹配的子字符串
public class BaseRegularExpressionsAPI {

    public static void main(String[] args) {
        String value = "this test line this test";

        // TODO. 非正则形式: 替换所有匹配的子字符串
        String result = value.replace("this", "OK");
        System.out.println(result);


        // TODO. 正则表达式形式
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

        // 按照匹配的字符串来分隔成字符数组
        String[] items = value.split("is");
        for (String item: items) {
            System.out.println(item);
        }
    }
}
