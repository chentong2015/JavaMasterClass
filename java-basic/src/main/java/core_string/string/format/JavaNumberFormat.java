package core_string.string.format;

import java.text.NumberFormat;
import java.util.Locale;

// NumberFormat 格式化数值的标准输出形式 1, 1K, 1M, 1B, 1T
public class JavaNumberFormat {

    public static void main(String[] args) {
        Locale locale = new Locale("en", "US");
        NumberFormat followers = NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT);
        System.out.println(followers.format(5412)); // 5K
        followers.setMaximumFractionDigits(2);
        System.out.println(followers.format(5412)); // 5.41K

        NumberFormat shortened = NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT);
        NumberFormat shortenedWithFraction = NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT);
        shortenedWithFraction.setMaximumFractionDigits(1);

        for (int exp = 0; exp <= 12; exp++)
            for (int secondDigit : new int[]{4, 5}) {
                double number = Math.pow(10, exp) + secondDigit * Math.pow(10, exp - 1);
                System.out.printf("%,f ~> %s / %s%n", number,
                        shortened.format(number),
                        shortenedWithFraction.format(number));
            }
    }
}
