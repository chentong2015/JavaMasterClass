package JavaRegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherHtmlText {

    private StringBuilder htmlText;

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

        // flags: Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        boolean isMatch = matcher.matches(); // Matches要求字符串全匹配, 而非子字符串

        // matcher 只能被使用一次，在调用matches()方法之后，需要重置 internal state
        matcher.reset();
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
}
