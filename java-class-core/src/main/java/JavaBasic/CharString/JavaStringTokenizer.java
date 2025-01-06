package JavaBasic.CharString;

import java.util.StringTokenizer;

// StringTokenizer将字符串转成Token的序列
// The string tokenizer class allows an application to break a string into tokens
public class JavaStringTokenizer {

    // 支持提供特殊的delimiters分隔符号
    public void testStringTokenizer() {
        StringTokenizer st = new StringTokenizer("this is a test");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

    // TODO. StringTokenizer的存在只是为了兼容老版本，推荐使用split()
    // String[] result = "this is a test".split("\\s");
    // for (int x=0; x<result.length; x++) {
    //     System.out.println(result[x]);
    // }
    public void testSplitString() {
        StringTokenizer stringTokenizer = new StringTokenizer("this, is, ok", ",");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
