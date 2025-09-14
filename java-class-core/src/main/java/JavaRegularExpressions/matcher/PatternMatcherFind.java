package JavaRegularExpressions.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO. 调用find()方法匹配子序列, 并非完全匹配正则的规则
// Attempts to find the next subsequence of the input sequence that matches the pattern.
public class PatternMatcherFind {

    public static void main(String[] args) {
        String str = "Greater Jakarta Metropolitan Regional";
        Pattern pTest = Pattern.compile("\\bJAKARTA");
        Matcher mTest = pTest.matcher(str.toLowerCase());
        System.out.println(mTest.find());

        // 提取正则表达式匹配的子序列中的数字
        String testStr1 = "abcd.135uvgh.7thwi.556";
        Pattern pattern1 = Pattern.compile("[A-Za-z]+\\.(\\d+)");
        Matcher matcher1 = pattern1.matcher(testStr1);

        int count = 0;
        while (matcher1.find()) {
            count++;
            System.out.println(matcher1.group(1));

            // 精确找到匹配的数字的起始位置
            int startIndex = matcher1.start(1);
            int endIndex = matcher1.end();
            System.out.println(startIndex + " - " + endIndex);
        }
        System.out.println(count);
    }
}
