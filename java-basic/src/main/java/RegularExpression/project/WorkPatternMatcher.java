package RegularExpression.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkPatternMatcher {

    // 循环匹配复合条件的子字符串, 使用()从1到N分组
    public static void main(String[] args) {
        Pattern myPattern = Pattern.compile("(IBAN:|CASE:)([^.]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = myPattern.matcher("test IBAN: 1BBQ. Case: 56HE. hello");
        while (matcher.find()) {
            String label = matcher.group(1).trim();
            System.out.println(label);

            String value = matcher.group(2).trim();
            System.out.println(value);
        }

        // 使用(?:)排除第一个分组, 只剩一个分组可以取值
        Pattern myPattern2 = Pattern.compile("(?:IBAN:|CASE:)([^.]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = myPattern2.matcher("test IBAN: 1BBQ. Case: 56HE. hello");
        while (matcher2.find()) {
            String value = matcher2.group(1).trim();
            System.out.println(value);
        }
    }
}
