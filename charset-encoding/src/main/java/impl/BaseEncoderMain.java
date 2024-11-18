package impl;

public class BaseEncoderMain {

    private static final char PADDING = '0';
    private static final String PREFIX = "X";

    public static void main(String[] args) {
        // Base36EncoderImpl encoder = new Base36EncoderImpl(3, PREFIX, PADDING);
        // System.out.println(encoder.encode(1000));
        // System.out.println(encoder.encode(1001));
        // System.out.println(encoder.encode(1002));

        // System.out.println(encoder.encode(1189));
        // System.out.println(encoder.encode(1190));
        // System.out.println(encoder.encode(1191));
        // System.out.println(encoder.encode(1192));

        // System.out.println(encoder.encode(1275));
        // System.out.println(encoder.encode(1295));
        // System.out.println(encoder.encode(1296));
    }

    // 一种自定义字符串的随机编码算法
    public static byte[] encodeStringText(String strText) {
        strText = strText.trim();
        byte[] bytes = new byte[strText.length() / 4];
        for (int i = 0; i < strText.length(); i += 4) {
            String str = strText.substring(i, i + 4);
            byte temp;
            try {
                temp = Byte.parseByte(str, 16);
            } catch (NumberFormatException var8) {
                int iByte = Integer.parseInt(str, 16);
                temp = (byte) (iByte - 256);
            }
            bytes[i / 4] = temp;
        }
        return bytes;
    }
}
