package JavaRegularExpression;

// TODO. Split API提供正则表达式来分隔字符串
public class RegularExpressionSplit {

    public static void main(String[] args) {
        // TODO. 以多个字符同时作为分割符
        String strSequence = "AAA;BBB,CCC|DDD";
        String[] strArray = strSequence.split("[;,|]");

        // 如果分隔点后没有数据，则只保留开头子字符串
        String info = "[item]";
        String[] infos = info.split("item]");
        System.out.println(infos.length); // "["

        // 匹配完整字符串作为正则表达式
        String value = "this is a test";
        String[] items = value.split("is"); // "th", " ", " a test"

        // 如果没有找到分割字符，则结果为原始字符串
        String valueStr = "this is a test";
        String[] itemsStr = valueStr.split(":");
        System.out.println(itemsStr.length); // "this is a test"
    }
}
