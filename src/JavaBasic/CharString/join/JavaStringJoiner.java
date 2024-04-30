package JavaBasic.CharString.join;

import java.util.StringJoiner;

// StringJoiner is used to construct a sequence of characters
// - separated by a delimiter
// - starting with a supplied prefix
// - ending with a supplied suffix.
public class JavaStringJoiner {

    // StringJoiner用于级联多个字符串, 将字符串存储在String[]
    // 等下于直接调用String.join(),
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("George").add("Sally").add("Fred");
        String desiredString = sj.toString();
        System.out.println(desiredString);
    }
}
