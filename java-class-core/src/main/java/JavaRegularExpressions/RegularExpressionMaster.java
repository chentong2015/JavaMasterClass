package JavaRegularExpressions;

public class RegularExpressionMaster {

    // TODO. 大小写敏感的匹配规则
    // (?idmsuxU-idmsuxU) 	Nothing, but turns match flags i d m s u x U on - off 取消匹配时的指定标识
    public static void main(String[] args) {
        // (?i) 取消大小写敏感性, i表示CASE_INSENSITIVE标识
        String res = args[0].replaceAll("(?i)[a-fA2-5]arry", "Harry");
        System.out.println(res);
    }

    // TODO. 正向前瞻(positive lookahead)的正则匹配
    // (?=X)  X, via zero-width positive lookahead  正向(look ahead)肯定预查: 使用Matcher匹配到的是字符的精确结尾位置 !!!
    // (?!X)  X, via zero-width negative lookahead  正向否定预查: "Not"的关系，可以匹配空
    // (?<=X) X, via zero-width positive lookbehind 反向(look behind)肯定预查
    // (?<!X) X, via zero-width negative lookbehind 反向否定预查
    private static void testSpecialConstructs(String alpha) {
        String regex1 = "Windows(?=95|98|NT|2000)"; // Windows2000
        String regex2 = "Windows(?!95|98|NT|2000)"; // Windows3.1, Windows
        String regex3 = "(?<=95|98|NT|2000)Windows"; // 2000Windows
        String regex4 = "(?<!95|98|NT|2000)Windows"; // 3.1Windows

        String regex5 = "^(?=.*\\d)"; // 字符串开头必须至少包含一个数字(\d)
        String regex6 = "^\\s*\\d";   // 字符串开头0个或多个空白, 后接至少一个数字
    }

    // TODO. [ /-, ] 方括号中的-表示前后字符的中间值，该正则表达式无效!!
    // ^ ... $               整个字符串必须完全匹配
    // (\{ ... \})+          外层要求大括号{...}这种结构至少一次，可以重复{...}{...}出现
    // \{ 和 \}              匹配字面量的大括号{ 和 }
    // (Company|Other|TEST)  冒号前的标签，必须是3个关键字之一
    // :                     冒号分隔符
    // [A-Za-z0-9 /, -]+     至少一个由字母、数字、空格、斜杠、逗号、连字符 - 组成的字符
    // (;[A-Za-z0-9 /, -]+)* 可选的分号分隔部分，第一个值后，可以有多个;值
    //
    // 正确案例
    // {Company:ABC}
    // {Other:John;Mary;Tom, }
    // {Company:ABC}{TEST:Jane -Doe}
    //
    // 错误案例
    // {Company:}
    // {Company:ABC;}
    // {Check:ABC}
    private static void testFullRegex() {
        String regex1 = "^(\\{(Company|Other|TEST):[A-Za-z0-9 /,-]+(;[A-Za-z0-9 /,-]+)*\\})+$)";
    }
}
