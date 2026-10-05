package GenericType.type_inference;

import java.util.List;
import java.util.stream.Collectors;

public class TypeInferenceStreams {

    private List<ListEntity> testToList1(List<String> values) {
        // return values.stream()
        //          .map(value -> new ListEntitySyn(value)) // Stream<ListEntitySyn> map的结果类型
        //          .toList(); // List<ListEntitySyn> 对象无法赋值给方法的返回类型

        return values.stream()
                // .<ListEntity>map(value -> new ListEntitySyn(value))     // 显式设置map的结果类型
                .map(value -> (ListEntity) new ListEntitySyn(value)) // 强制转换成结果类型
                .toList(); // List<ListEntity> 对象可以赋值给方法的返回类型
    }

    // TODO. 通过泛型的类型推断返回正确的结果
    private List<ListEntity> testToList2(List<String> values) {
        return values.stream()
                .map(value -> new ListEntitySyn(value))
                .collect(Collectors.toList());

        // collect()泛型方法推断，A是List累积容器
        // <R, A> R collect(Collector<? super T, A, R> collector);
        // <R, A> R collect(Collector<? super ListEntitySyn, A, R> collector);
        // <A> List<ListEntity> collect(Collector<? super ListEntitySyn, A, List<ListEntity>> collector);

        // collector泛型方法参数推断
        // <X> Collector<X, ?, List<X>> toList()
        // <ListEntity> Collector<ListEntity, ?, List<ListEntity>> toList()
        // Collectors.<ListEntity>toList()
    }

    static class ListEntity {
        protected int id;
    }

    static class ListEntitySyn extends ListEntity {
        private final String name;

        public ListEntitySyn(String name) {
            this.name = name;
        }
    }
}
