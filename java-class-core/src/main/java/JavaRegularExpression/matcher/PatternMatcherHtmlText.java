package JavaRegularExpression.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherHtmlText {

    private StringBuilder htmlText;

    public static void main(String[] args) {
        PatternMatcherHtmlText instance = new PatternMatcherHtmlText();
        instance.htmlText = new StringBuilder("<h1>My Heading</h1>");
        instance.htmlText.append("<h2>Sub Heading</h2>");
        instance.htmlText.append("<p>This is a paragraph about something</p>");
        instance.htmlText.append("<p>This ia another paragraph</p>");
        instance.htmlText.append("<h2>Summary</h2>");
        instance.testMatcherGroupMethods();
    }

    // TODO. find()查询后提取特定分组中匹配的数据结果
    // Long Occurrence: <h2>Sub Heading</h2>
    // Long Occurrence: <h2>Summary</h2>
    // Short Occurrence: Sub Heading
    // Short Occurrence: Summary
    private void testMatcherGroupMethods() {
        String h2GroupPattern = "(<h2>.*</h2>)";   // 贪婪模式，匹配从开头便签到结尾标签之间的所有字符
        String h2GroupPatternShort = "(<h2>.*?</h2>)"; // 非贪婪模式，最短匹配，统计在第一组

        Pattern groupPattern = Pattern.compile(h2GroupPatternShort);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        while (groupMatcher.find()) {
            System.out.println("Long Occurrence: " + groupMatcher.group(0));
        }

        String h2TextGroups = "(<h2>)(.*?)(</h2>)"; // 使用分组，只提取指定标签中的内容信息
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);
        while (h2TextMatcher.find()) {
            System.out.println("Short Occurrence: " + h2TextMatcher.group(2));
        }
    }
}
