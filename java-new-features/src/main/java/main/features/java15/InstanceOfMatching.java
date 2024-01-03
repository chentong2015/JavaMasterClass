package main.features.java15;

public class InstanceOfMatching {

    // 在判断instanceof的同时完成类型转换, 避免强转时的类型异常
    // 同时支持附加判断条件
    public void testPattern(Object obj) {
        if (obj instanceof String s && s.length() > 10) {
            // can use s here. No need to cast
            int length = s.length();
        } else {
            // can't use s here
        }
    }
}
