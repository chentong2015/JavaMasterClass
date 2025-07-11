package JavaRegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// TODO. Pattern.compile(regex)可能造成性能问题
// 创建Pattern的成本很高，需要将正则表达式编译成state machine状态机
public class PatternMatcherCore {

    // TODO. Pattern是正则表达式的"编译表示", 代替正则表达式的字符串的使用
    // 1. 将指定为字符串的"正则表达式"编译为Pattern实例
    // 2. 将所得模式用于创建Matcher对象，该对象可以将任意字符序列与正则表达式进行匹配
    // 3. 进行匹配所涉及的所有状态都位于匹配器中，许多匹配器可以共享同一模式
    public static boolean matches(String regex, CharSequence input) {
        // 指定Pattern比较的flag标签
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    // TODO. 将正则匹配规则转换成Predicate作为过滤条件
    private void testPatternToPredicate() {
        Pattern nonWordCharacter = Pattern.compile("\\W");
        Stream.of("Metallica", "Made")
                .filter(nonWordCharacter.asPredicate())
                .forEach(System.out::println);
    }

    // TODO. Matches()要求字符串全匹配, 而非子字符串
    // matcher只能被使用一次，在调用matches()方法之后，需要重置internal state
    public static void main(String[] args) {
        Pattern p = Pattern.compile("a*b");
        Matcher matcher = p.matcher("aab");
        boolean isMatch = matcher.matches();
        matcher.reset();

        String testStr2 = "abcd.135\tuvgh.7\tthwi.556\n";
        Pattern pattern2 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");

        // group 1 + group 2
        String testStr3 = "{1, 2}, {5, 6}, {5, 7}";
        Pattern pattern3 = Pattern.compile("\\{(.+?)\\}");
        Pattern pattern4 = Pattern.compile("\\{(\\d+, \\d+)\\}");
        Pattern pattern5 = Pattern.compile("\\{(\\d+), (\\d+)\\}");

        // ? 作用在group分组上面，零次或者一次
        String testStr4 = "1111";
        String testStr5 = "1111-111";
        Pattern pattern6 = Pattern.compile("^\\d{4}(-\\d{3})?$");
    }
}
