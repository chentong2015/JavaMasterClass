package JavaRegularExpression;

public class RegularExpressionProjects {

    // TODO. 大小写敏感的匹配规则
    // (?i) 取消大小写敏感性, i表示CASE_INSENSITIVE标识
    // (?idmsuxU-idmsuxU) 	Nothing, but turns match flags i d m s u x U on - off
    public static void main(String[] args) {
        String res = args[0].replaceAll("(?i)[a-fA2-5]arry", "Harry");
        System.out.println(res);
    }

    // TODO. 正向前瞻匹配
    // (?=X)  X, via zero-width positive lookahead  正向肯定预查: Matcher匹配字符精确结尾位置
    // (?!X)  X, via zero-width negative lookahead  正向否定预查: "Not"的关系，可以匹配空
    // (?<=X) X, via zero-width positive lookbehind 反向肯定预查
    // (?<!X) X, via zero-width negative lookbehind 反向否定预查
    private static void testSpecialConstructs(String alpha) {
        String regex1 = "Windows(?=95|98|NT|2000)"; // Windows2000
        String regex2 = "Windows(?!95|98|NT|2000)"; // Windows3.1, Windows
        String regex3 = "(?<=95|98|NT|2000)Windows"; // 2000Windows
        String regex4 = "(?<!95|98|NT|2000)Windows"; // 3.1Windows
    }

    // "^(?=.*\\d)"; // 字符串开头必须至少包含一个数字(\d)
    // "^\\s*\\d";   // 字符串开头0个或多个空白, 后接至少一个数字

    // TODO. [ /-, ] 方括号中'-'表示从什么字符到什么字符，可能导致整个表达式无效
    // "^(\\{(Company|Other|TEST):[A-Za-z0-9 /,-]+(;[A-Za-z0-9 /,-]+)*\\})+$)"
    //   ^ ... $               整个字符串必须完全匹配
    //   (\{ ... \})+          外层要求大括号{...}这种结构至少一次，可以重复{...}{...}出现
    //   \{ 和 \}              匹配字面量的大括号{ 和 }
    //   (Company|Other|TEST)  冒号前的标签，必须是3个关键字之一
    //   :                     冒号分隔符
    //   [A-Za-z0-9 /, -]+     至少一个由字母、数字、空格、斜杠、逗号、连字符 - 组成的字符
    //   (;[A-Za-z0-9 /, -]+)* 可选的分号分隔部分，第一个值后，可以有多个;值

    // "(\([^\)]*?\))" 匹配括号和括号中所有字符 !!
    // (
    //   \(       左括号
    //     [^\)]  任意数量的非')'字符
    //   \)       右括号
    // )
}
