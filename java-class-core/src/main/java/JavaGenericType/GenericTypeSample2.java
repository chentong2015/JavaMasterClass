package JavaGenericType;

import java.util.Collection;

// TODO. 使用"参数列表"来定义泛型方法的重载，可能造成问题
// Possible heap pollution from parameterized vararg type
public class GenericTypeSample2<E extends Enum<E>> {

    protected String buildSelectQuery(Enum<E>... columnNames) {
        System.out.println("OK 2");
        return null;
    }

    protected String buildSelectQuery(String condition, Enum<E>... columnNames) {
        System.out.println("OK ");
        return null;
    }


    protected String buildUpdateQuery(String condition, Enum<E>... columnNames) {
        System.out.println("OK 3");
        return null;
    }

    protected String buildUpdateQuery(String condition, Collection<E> collection) {
        System.out.println("OK 4");
        return null;
    }
}
