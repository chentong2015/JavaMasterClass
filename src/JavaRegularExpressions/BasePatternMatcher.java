package JavaRegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Pattern: 正则表达式的"编译表示", 代替正则表达式的字符串的使用
public class BasePatternMatcher {

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

    private void testPattern() {
        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub Heading</h2>");
        htmlText.append("<p>This is a paragraph about something</p>");
        htmlText.append("<p>This ia another paragraph</p>");
        htmlText.append("<h2>Summary</h2>");

        String h2Pattern = ".*<h2>.*";
        Pattern pattern = Pattern.compile(h2Pattern); // flags: Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
        Matcher matcher = pattern.matcher(htmlText);
        boolean isMatch = matcher.matches(); // Matches要求字符串全匹配, 而非子字符串
    }

}
