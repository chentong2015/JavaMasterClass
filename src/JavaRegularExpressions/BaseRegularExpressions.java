package JavaRegularExpressions;

// 正则表达式: 用于搜索，编辑或处理文本和数据
// 一种基于一组字符串中每个字符串共有的"共同特征"来描述一组字符串的方法, 字符串的pattern
// https://zh.wikipedia.org/wiki/正则表达式
public class BaseRegularExpressions {

    private static String alpha = "Abcdeeefffggh";

    // Regex Expression 默认大小写敏感的
    private static void testRegularExpressions() {
        boolean isMatched = alpha.matches("^acb"); // 判断正则是否匹配字符串, 必须全部字符都匹配 !!!

        String result1 = alpha.replace(".", "Y"); // . 匹配除“\r”“\n”之外的任何单个字符
        String result2 = alpha.replace("^Abc", "U"); // ^ 匹配字符串的开头
        String result3 = alpha.replace("gh$", "end"); // $ 匹配字符串的结尾
        String result4 = alpha.replace("[bcf]", "X"); // [] 匹配指定范围内的(单个)字符
        String result5 = alpha.replace("[bcf][fh]", "X"); // bf, cf, ff, bh, ch, fh
        String result6 = "Harry".replaceAll("[Hh]arry", "Harry");
        String result7 = "Harry".replaceAll("[a-fA-F2-5]arry", "Harry"); // [ - ] 匹配指定的字符区间
        String result8 = "Harry".replaceAll("(?i)[a-fA2-5]arry", "Harry"); // (?i) 取消大小写敏感性

        String result9 = alpha.replaceAll("[^eg]", "X"); // 排除型字符集合(negated character classes), 替换除了e, g以外的其它所有的字符
        String result10 = alpha.replaceAll("\\d", "X"); // \d 匹配所有的数字
        String result11 = alpha.replaceAll("\\D", "X"); // \D 匹配所有的非数字的字符
        String result12 = alpha.replaceAll("\\w", "X"); // \w 匹配包括下划线的任何单词字符 => 等价于“[A-Za-z0-9_]”
        String result13 = alpha.replaceAll("\\W", "X"); // \W 匹配任何非单词字符 => 等价于“[^A-Za-z0-9_]”
        String result14 = alpha.replaceAll("\\b", "X"); // \b 匹配一个单词边界 => 对单词进行标注/添加标签，特别运用在Html的Tag中
    }

    private static void testRemoveWhiteSpace() {
        String testStr = "I have blanks\ta tab and also a newline\n";
        String result1 = testStr.replaceAll("\t", ""); // “\t” 匹配一个制表符
        String result2 = testStr.replaceAll("\n", ""); // “\n” 匹配一个换行符
        String result3 = testStr.replaceAll("\\s", ""); // 匹配任何空白字符, 包括空格, 制表符, 换页符等等 => 等价于[\f\n\r\t\v]
        String result4 = testStr.replaceAll("\\S", ""); // 匹配任何非空白字符 => 等价于[^ \f\n\r\t\v]
    }

    private static void testQuantifiers() {
        String result = alpha.replaceAll("^abe{3}", "XXX"); // { } 指定前面一个字符重复的次数
        String result1 = alpha.replaceAll("^abe?", "XXX");  // ? 匹配前面的字符零次或一次
        String result2 = alpha.replaceAll("^abe*", "XXX");  // * 匹配前面的字符零次或多次
        String result3 = alpha.replaceAll("^abe+", "XXX");  // + 匹配前面的字符一次或多次
        String result4 = alpha.replaceAll("^abe{2,5}", "XXX");  // {n, m} 最少匹配n次且最多匹配m次
    }

}
