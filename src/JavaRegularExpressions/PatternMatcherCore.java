package JavaRegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// TODO. Pattern.compile(regex)可能造成性能问题
// 创建Pattern的成本很高，需要将正则表达式编译成state machine状态机
public class PatternMatcherCore {

    // TODO. Pattern是正则表达式的"编译表示", 代替正则表达式的字符串的使用
    // 1. 先将指定为字符串的"正则表达式"编译为Pattern实例
    // 2. 然后将所得的模式用于创建Matcher对象，该对象可以将任意字符序列与正则表达式进行匹配
    // 3. 进行匹配所涉及的所有状态都位于匹配器中，因此许多匹配器可以共享同一模式
    public static boolean matches(String regex, CharSequence input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    // 可以将正则的匹配规则转换成Predicate<String>, 从而作为过滤的条件
    private void testPatternToPredicate() {
        Pattern nonWordCharacter = Pattern.compile("\\W");
        Stream.of("Metallica", "Made")
                .filter(nonWordCharacter.asPredicate())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aab");
        boolean b = m.matches();

        String testStr1 = "abcd.135uvgh.7thwi.556";
        Pattern pattern1 = Pattern.compile("[A-Za-z]+\\.(\\d+)");
        Matcher matcher1 = pattern1.matcher(testStr1);
        while (matcher1.find()) {
            System.out.println(matcher1.group(1)); // 匹配出所有数字
        }

        String testStr2 = "abcd.135\tuvgh.7\tthwi.556\n";
        Pattern pattern2 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");

        String testStr3 = "{1, 2}, {5, 6}, {5, 7}";
        Pattern pattern3 = Pattern.compile("\\{(.+?)\\}");
        Pattern pattern4 = Pattern.compile("\\{(\\d+, \\d+)\\}");
        Pattern pattern5 = Pattern.compile("\\{(\\d+), (\\d+)\\}"); // group 1 + group 2

        String testStr4 = "1111";
        String testStr5 = "1111-111";
        Pattern pattern6 = Pattern.compile("^\\d{4}(-\\d{3})?$"); // ? 作用在group分组上面，零次或者一次
    }
}
