package JavaGenericType;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericTypeSample1 {

    // TODO. 无法直接创建泛型类型的数组，使用泛型列表来替代
    // List<Class<E>> list = List.of(); 限定了类型约束E
    // Class<E>[] array = new Class<E>[10];
    public static <E extends Enum<E>, U extends Enum<U>> Set<String> exclude(Class<E> e1, Class<U> e2) {
        List<Class<E>> list = List.of(e1);
        return exclude(list, e2);
    }

    // 以下转换将抛出Unchecked的提示
    // Class -> Class<E> throws Unchecked method invocation
    public static <E extends Enum<E>, U extends Enum<U>> Set<String> exclude(List<Class<E>> es, Class<U> e) {
       return es.stream()
          .map(Class::getEnumConstants)
          .flatMap(Stream::of)
          .map(Enum::name)
          .filter(not(asNameSet(e)::contains))
          .collect(Collectors.toSet());
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    public static <E extends Enum<E>> Set<String> asNameSet(Class<E> e) {
        return Stream.of(e.getEnumConstants())
          .map(Enum::name)
          .collect(Collectors.toSet());
    }
}
