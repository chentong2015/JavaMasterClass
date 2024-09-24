package JavaRegularExpressions;

// https://zh.wikipedia.org/wiki/正则表达式
// 1. 用于搜索，编辑或处理文本和数据
// 2. 一种基于一组字符串中每个字符串"共同特征"来描述所有字符串
// 3. 正则表达式是字符串一种pattern，默认大小写敏感
public class BaseRegularExpressions {

    private static void testRegularExpressions() {
        String alpha = "Abcdeeefffggh";
        boolean isMatched = alpha.matches("^acb"); // 判断正则是否匹配字符串, 必须全部字符都匹配 !!!

        String result1 = alpha.replace(".", "Y"); // . 匹配除“\r”“\n”之外的任何单个字符  ==> \\. 只匹配点
        String result2 = alpha.replace("^Abc", "U"); // ^ 匹配字符串的开头  ==> "Not"的关系
        String result3 = alpha.replace("gh$", "end"); // $ 匹配字符串的结尾

        String result4 = alpha.replace("[bcf]", "X"); // [] 匹配指定范围内的(单个)字符 => "或"的关系 [b|c|f]
        String result5 = alpha.replace("[bcf][fh]", "X"); // bf, cf, ff, bh, ch, fh
        String result6 = "Harry".replaceAll("[Hh]arry", "Harry");
        String result7 = "Harry".replaceAll("[a-fA-F2-5]arry", "Harry"); // [ - ] 匹配指定的字符区间

        // 排除型字符集合(negated character classes), 匹配除了e, g以外的其它所有的字符, 但必须匹配一个字符 !!
        String result8 = alpha.replaceAll("[^eg]", "X");

        String result9 = alpha.replaceAll("\\d", "X"); // \d 匹配所有的数字
        String result10 = alpha.replaceAll("\\D", "X"); // \D 匹配所有的非数字的字符
        String result11 = alpha.replaceAll("\\w", "X"); // \w 匹配包括下划线的任何单词字符 => 等价于“[A-Za-z0-9_]”
        String result12 = alpha.replaceAll("\\W", "X"); // \W 匹配任何非单词字符 => 等价于“[^A-Za-z0-9_]”
        String result13 = alpha.replaceAll("\\b", "X"); // \b 匹配一个单词边界 => 对单词进行标注/添加标签，特别运用在Html的Tag中
    }

    private static void testRemoveWhiteSpace() {
        String testStr = "I have blanks\ta tab and also a newline\n";
        String result1 = testStr.replaceAll("\t", ""); // “\t” 匹配一个制表符
        String result2 = testStr.replaceAll("\n", ""); // “\n” 匹配一个换行符
        String result3 = testStr.replaceAll("\\s", ""); // 匹配任何空白字符, 包括空格, 制表符, 换页符等等 => 等价于[\f\n\r\t\v]
        String result4 = testStr.replaceAll("\\S", ""); // 匹配任何非空白字符 => 等价于[^ \f\n\r\t\v]
    }

    private static void testQuantifiers() {
        String alpha = "Abcdeeefffggh";
        String result = alpha.replaceAll("^abe{3}", "XXX"); // { } 指定前面一个字符重复的次数
        String result1 = alpha.replaceAll("^abe?", "XXX");  // ? 匹配前面的字符零次或一次
        String result2 = alpha.replaceAll("^abe*", "XXX");  // * 匹配前面的字符零次或多次
        String result3 = alpha.replaceAll("^abe+", "XXX");  // + 匹配前面的字符一次或多次 
        String result4 = alpha.replaceAll("^abe{2,5}", "XXX");  // {n, m} 最少匹配n次且最多匹配m次
    }

    // ( ) 括号表示分组，其中左右括号不作为匹配的字符考虑  ===> 如果要匹配( ), 可以使用[\(] [\)], [\\(] [\\)]
    // Non-greedy 非贪婪模式：尽可能少的匹配所搜索的字符串 (.*?) 任意字符的0次或者多次，尽量少的进行匹配
    // Greedy 贪婪模式: 尽可能多的匹配所搜索的字符串  (.*)? 匹配任意的一组字符, 0次或者多次, 可能是一行的数据 ==> . 无法匹配换行符\n
    private static void testGreedyQuantifiers() {
        String str = "oooo";
        String result1 = str.replaceAll("o+", "x"); // 匹配所有的o
        String result2 = str.replaceAll("o+?", "x"); // 匹配单个的o

        String str2 = "eeAiiZooAuuZZeeeZZfff\n";
        String result3 = str2.replaceAll("A[^Z]*ZZ", "X"); // AuuZZ
        String result4 = str2.replaceAll("A.*?ZZ", "X"); // AiiZooAuuZZ 非贪婪模式
        String result5 = str2.replaceAll("A.*?Z", "X"); // AiiZ和AuuZ 非贪婪模式
        String result6 = str2.replaceAll("A.*ZZ", "X"); // AiiZooAuuZZeeeZZ 贪婪模式
    }

    /**
     * (?idmsuxU-idmsuxU) 	Nothing, but turns match flags i d m s u x U on - off 取消匹配时的指定标识
     * (?=X)	X, via zero-width positive lookahead 正向（look ahead）肯定预查  ===> 使用Matcher匹配到的是字符的精确结尾位置 !!!
     * (?!X)	X, via zero-width negative lookahead 正向否定预查                ===> "Not"的关系，可以匹配空
     * (?<=X)	X, via zero-width positive lookbehind 反向（look behind）肯定预查
     * (?<!X)	X, via zero-width negative lookbehind 反向否定预查
     */
    private static void testSpecialConstructs() {
        // (?i) 取消大小写敏感性 => i表示CASE_INSENSITIVE标识
        String result8 = "Harry".replaceAll("(?i)[a-fA2-5]arry", "Harry");

        String regex1 = "Windows(?=95|98|NT|2000)"; // Windows2000
        String regex2 = "Windows(?!95|98|NT|2000)"; // Windows3.1, Windows
        String regex3 = "(?<=95|98|NT|2000)Windows"; // 2000Windows
        String regex4 = "(?<!95|98|NT|2000)Windows"; // 3.1Windows
    }
}
