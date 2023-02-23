package java15;

public class InstanceofMatching {

    // Pattern matching : Avoid casting subsequent to instanceof
    // 在判断instanceof的同时自定完成类型转换, 并且附加判断条件
    public void testPattern(Object obj) {
        if (obj instanceof String s && s.length() > 10) {
            // can use s here. No need to cast
            int length = s.length();
        } else {
            // can't use s here
        }
    }
}
