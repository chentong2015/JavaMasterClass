package JavaFeatureOopClass.Encapsulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// public的类型，做为package打包提供的API，需要对外提供长期的维护
public class JavaEncapsulation {

    // 长度非0的数组总是可变的，数组的内容可被修改 => 错误
    public static final String[] VALUES = {"", ""};

    private static final String[] VALUES_PRIVATE = {"", ""};

    // 1. 设置私有的数组
    public static final List<String> LIST = Collections.unmodifiableList(Arrays.asList(VALUES_PRIVATE));

    // 2. 返回私有数组的拷贝
    public static final String[] values() {
        return VALUES_PRIVATE.clone();
    }
}
