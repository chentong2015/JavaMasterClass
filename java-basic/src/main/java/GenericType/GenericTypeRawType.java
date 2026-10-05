package GenericType;

import java.util.ArrayList;
import java.util.List;

// TODO. 不推荐再使用Raw Type类型, 而是提供泛型特化类型
public class GenericTypeRawType {

    public static void main(String[] args) {
        // Raw Type 原始类型
        // - 兼容Java 5之前没有泛型的老代码
        // - 失去泛型提供的类型安全检查
        List listRawType = new ArrayList();
        listRawType.add("hello");
        listRawType.add(123);

        // 参数化类型: 提前在编译阶段包装类型安全
        List<String> listGeneric = new ArrayList<>();
    }
}
