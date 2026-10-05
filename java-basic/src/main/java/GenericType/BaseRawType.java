package GenericType;

import java.util.ArrayList;
import java.util.List;

// TODO. 不推荐再使用Raw Type类型, 而是提供泛型特化类型
// - 泛型参数被特化后将会对实参进行Check，以保证类型一致
// - 原始类型不会对实参做check，不会保证数据类型的安全性
public class BaseRawType {

    // TODO. 具有泛型参数的类型在被实例化时，泛型参数需要被特化
    // Box类型是泛型类型Box<T>的原始类型(raw type)，类型参数为Object
    static class Box<T> {
        private T info;

        public void set(T t) {
            info = t;
        }
    }

    public static void main(String[] args) {
        // Raw Type 原始类型
        // - 兼容Java 5之前没有泛型的老代码
        // - 失去泛型提供的类型安全检查
        List listRawType = new ArrayList();
        listRawType.add("hello");
        listRawType.add(123);

        Box rawBox = new Box<>();
        rawBox.set(100);
        rawBox.set("test"); // 原始类型在调用泛型方法时，不做类型验证

        // 参数化类型: 提前在编译阶段包装类型安全
        List<String> listGeneric = new ArrayList<>();
    }
}
