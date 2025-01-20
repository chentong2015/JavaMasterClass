package codec;

// TODO. Base32 Encoding (0-9, A-Z)
// Base32使用32种字符来编码原始字符串，生成的结果由36种字符的组合而成
public class JavaBase36Encoder {

    private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encode(long id) {
        // Checking the validation of id
        StringBuilder sb = new StringBuilder();
        long value = id;
        while (value > 0) {
            int remainder = (int) (value % 36);
            sb.append(DIGITS.charAt(remainder));
            value = value / 36;
        }
        String result = sb.toString();
        if (result.length() > 10) {
           throw new RuntimeException("Encode value is greater than expected length");
        }
        return result;
    }

    public long decode(String str) {
        long result = 0;
        for (char c: str.toCharArray()) {
            long digit = DIGITS.indexOf(c);
            result = result * 36 + digit;
        }
        return result;
    }
}
