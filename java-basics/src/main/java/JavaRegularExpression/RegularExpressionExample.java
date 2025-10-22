package JavaRegularExpression;

public class RegularExpressionExample {

    // TODO. 注意转译字符的使用
    // \\. 匹配.点字符
    // .   匹配除“\r”“\n”之外的任何单个字符, 无法匹配换行符\n
    private static void testRegularExpressions(String alpha) {
        String res1 = alpha.replaceAll("\\.", "Y");

        String res2 = alpha.replaceAll("^Abc", "U"); // ^ 匹配字符串的开头  ==> "Not"的关系
        String res3 = alpha.replaceAll("gh$", "end"); // $ 匹配字符串的结尾
        String res4 = alpha.replaceAll("[bcf]", "X"); // [] 匹配指定范围内的(单个)字符 => "或"的关系 [b|c|f]
        String res5 = alpha.replaceAll("[bcf][fh]", "X"); // bf, cf, ff, bh, ch, fh

        String res6 = "Harry".replaceAll("[Hh]arry", "Harry");
        String res7 = "Harry".replaceAll("[a-fA-F2-5]arry", "Harry"); // [ - ] 匹配指定的字符区间

        // 排除型字符集合(negated character classes), 匹配除了e, g以外的其它所有的字符, 但必须匹配一个字符 !!
        String res8 = alpha.replaceAll("[^eg]", "X");

        String res9 = alpha.replaceAll("\\d", "X"); // \d 匹配所有的数字
        String res10 = alpha.replaceAll("\\D", "X"); // \D 匹配所有的非数字的字符
        String res11 = alpha.replaceAll("\\w", "X"); // \w 匹配包括下划线的任何单词字符 => 等价于“[A-Za-z0-9_]”
        String res12 = alpha.replaceAll("\\W", "X"); // \W 匹配任何非单词字符 => 等价于“[^A-Za-z0-9_]”
        String res13 = alpha.replaceAll("\\b", "X"); // \b 匹配一个单词边界 => 对单词进行标注/添加标签，特别运用在Html的Tag中
    }

    private static void testRemoveWhiteSpace() {
        String str = "I have blanks\ta tab and also a newline\n";
        String res1 = str.replaceAll("\t", ""); // “\t” 匹配一个制表符
        String res2 = str.replaceAll("\n", ""); // “\n” 匹配一个换行符
        String res3 = str.replaceAll("\\s", ""); // 匹配任何空白字符, 包括空格, 制表符, 换页符等等 => 等价于[\f\n\r\t\v]
        String res4 = str.replaceAll("\\S", ""); // 匹配任何非空白字符 => 等价于[^ \f\n\r\t\v]
    }

    private static void testQuantifiers() {
        String alpha = "Abcdeeefffggh";
        String res = alpha.replaceAll("^abe{3}", "XXX"); // { } 指定前面一个字符重复的次数
        String res1 = alpha.replaceAll("^abe?", "XXX");  // ? 匹配前面的字符零次或一次
        String res2 = alpha.replaceAll("^abe*", "XXX");  // * 匹配前面的字符零次或多次
        String res3 = alpha.replaceAll("^abe+", "XXX");  // + 匹配前面的字符一次或多次
        String res4 = alpha.replaceAll("^abe{2,5}", "XXX");  // {n, m} 最少匹配n次且最多匹配m次
    }

    // ( )        括号表示分组，其中左右括号不作为匹配的字符考虑  ===> 如果要匹配( ), 可以使用[\(] [\)], [\\(] [\\)]
    // Non-greedy 非贪婪模式：尽可能少的匹配所搜索的字符串 (.*?) 任意字符的0次或者多次，尽量少的进行匹配
    // Greedy     贪婪模式: 尽可能多的匹配所搜索的字符串  (.*)? 匹配任意的一组字符, 0次或者多次, 可能是一行的数据
    private static void testGreedyQuantifiers() {
        String str = "oooo";
        String res1 = str.replaceAll("o+", "x"); // 匹配所有的o
        String res2 = str.replaceAll("o+?", "x"); // 匹配单个的o

        String str2 = "eeAiiZooAuuZZeeeZZfff\n";
        String res3 = str2.replaceAll("A[^Z]*ZZ", "X"); // AuuZZ
        String res4 = str2.replaceAll("A.*?ZZ", "X"); // AiiZooAuuZZ 非贪婪模式
        String res5 = str2.replaceAll("A.*?Z", "X"); // AiiZ和AuuZ 非贪婪模式
        String res6 = str2.replaceAll("A.*ZZ", "X"); // AiiZooAuuZZeeeZZ 贪婪模式
    }
}
