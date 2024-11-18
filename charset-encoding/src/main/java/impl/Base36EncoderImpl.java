package impl;

// 一种编码算法，生成基于32位的编码结果
public class Base36EncoderImpl {

    private static final long BASE = 36;
    private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final int length;
    private final String prefix;
    private final char padding;
    private final String regex;

    public Base36EncoderImpl(int length, String prefix, char padding) {
        this.length = length;
        this.prefix = prefix;
        this.padding = padding;
        this.regex = "^" + prefix + "[0-9A-Z]+$";
    }

    public String encode(final long id) {
        if (id < 0) {
            throw new IllegalArgumentException("value to encode '" + id + "' not valid");
        }
        if (id == 0) {
            String result = Character.toString(DIGITS.charAt(0));
            return addPrefixedPadding(result);
        }

        long value = id;
        StringBuilder sb = new StringBuilder("");
        while (value > 0) {
            int remainder = (int) (value % BASE);
            sb.append(DIGITS.charAt(remainder));
            value = value / BASE;
        }
        String result = addPrefixedPadding(sb.reverse().toString());
        if (result.length() > length) {
           throw new RuntimeException("Encode value is greater than expected length");
        }
        return result;
    }

    private String addPrefixedPadding(String str) {
        StringBuilder sb = new StringBuilder(prefix);
        int paddingLength = length - prefix.length() - str.length();
        sb.append(String.valueOf(padding).repeat(Math.max(0, paddingLength)));
        return sb.append(str).toString();
    }

    public long decode(final String str) {
        if (str == null || !str.matches(regex)) {
            throw new IllegalArgumentException("value '" + str + "' not valid, expected to match '" + regex + "'");
        }
        if (str.length() > length) {
            throw new IllegalArgumentException("value '" + str + "' length greater than expected length '" + length + "'");
        }
        String value = str.substring(prefix.length());
        long result = 0;
        for (int i = 0; i < value.length(); i++) {
            long digit = DIGITS.indexOf(value.charAt(i));
            result = result * BASE + digit;
        }
        return result;
    }
}
