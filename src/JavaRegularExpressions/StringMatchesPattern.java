package JavaRegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatchesPattern {

    public static void main(String[] args) {
        String value = "test";
        boolean isMatch = value.matches("^es");
        System.out.println(isMatch);
    }

    // String.matches的源码实现: 背后会创建Pattern实例
    public boolean matches(String regex) {
        return Pattern.matches(regex, null);
    }

    public static boolean matches(String regex, CharSequence input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
