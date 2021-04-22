package JavaRegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// Pattern: 正则表达式的"编译表示", 代替正则表达式的字符串的使用
public class BasePatternMatcher {

    private StringBuilder htmlText;

    /**
     * 1. 必须先将指定为字符串的正则表达式编译为Pattern的实例
     * 2. 然后将所得的模式用于创建Matcher对象，该对象可以将任意字符序列与正则表达式进行匹配
     * 3. 进行匹配所涉及的所有状态都位于匹配器中，因此许多匹配器可以共享同一模式
     */
    // public Matcher matcher(CharSequence input) {}
    // interface CharSequence 字符序列 => 可以使用String, StringBuilder, StringBuffer
    private void testPatternMatcher() {
        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aab");
        boolean b = m.matches();
    }

    // 可以将正则的匹配规则转换成Predicate<String>, 从而作为过滤的条件
    private void testPatternToPredicate() {
        Pattern nonWordCharacter = Pattern.compile("\\W");
        Stream.of("Metallica", "Made")
                .filter(nonWordCharacter.asPredicate())
                .forEach(System.out::println);
    }

    private void initHtmlText() {
        htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub Heading</h2>");
        htmlText.append("<p>This is a paragraph about something</p>");
        htmlText.append("<p>This ia another paragraph</p>");
        htmlText.append("<h2>Summary</h2>");
    }

    private void testMatcherFindMethod() {
        String h2Pattern = ".*<h2>.*"; // 匹配htmlText整个字符串
        String h2PatternTag = "<h2>"; // 只匹配指定的标签
        Pattern pattern = Pattern.compile(h2Pattern); // flags: Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
        Matcher matcher = pattern.matcher(htmlText);
        boolean isMatch = matcher.matches(); // Matches要求字符串全匹配, 而非子字符串
        matcher.reset(); // matcher 只能被使用一次，在调用matches()方法之后，需要重置 internal state
        int count = 0;
        while (matcher.find()) {
            count++;
            int startIndex = matcher.start(1); // 只取第一组里面的index
            int endIndex = matcher.end(); // 匹配的最后一个字符的后一个index位置
        }
        matcher.reset();
        matcher.replaceAll("NewH2"); // 替换所有匹配到的字符
    }

    private void testMatcherGroupMethods() {
        String h2GroupPattern = "(<h2>.*</h2>)"; // 贪婪模式，匹配从开头便签到结尾标签之间的所有字符
        String h2GroupPattern2 = "(<h2>.*?</h2>)"; // 非贪婪模式，最短匹配，统计在第一组
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        while (groupMatcher.find()) {
            System.out.printf("Occurrence: " + groupMatcher.group(0));
        }

        String h2TextGroups = "(<h2>)(.*?)(</h2>)"; // 使用分组，只提取指定标签中的内容信息
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);
        while (h2TextMatcher.find()) {
            System.out.println("Occurrence: " + h2TextMatcher.group(2));
        }
    }

    private void testExamples() {
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
