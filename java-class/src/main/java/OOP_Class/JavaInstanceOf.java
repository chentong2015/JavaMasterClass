package OOP_Class;

// instanceof 用于判断对象是否属于特定类型
public class JavaInstanceOf {

    // TODO. Java 15 feature: Pattern Matching InstanceOf
    // 在判断instanceof的同时完成类型转换
    // 避免强转时的类型异常, 同时支持附加判断条件
    public void testPattern(Object obj) {
        if (obj instanceof String s && s.length() > 10) {
            // can use s here. No need to cast
            int length = s.length();
        } else {
            // can't use s here
        }
    }
}