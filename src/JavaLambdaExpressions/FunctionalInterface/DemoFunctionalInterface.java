package JavaLambdaExpressions.FunctionalInterface;

import java.util.function.Function;

public class DemoFunctionalInterface {

    public void testMyComparator() {
        Function<Person, Integer> funAge = Person::getAge;
        MyComparator<Person> cmpPersonAge = MyComparator.comparing(funAge);
        MyComparator<Person> cmpPersonName = MyComparator.comparing(Person::getName);
        // 连续同时比较多个条件
        MyComparator<Person> mixCmpPerson = cmpPersonAge.thenComparing(cmpPersonName);
        // Builder模式的使用
        MyComparator<Person> buildCmpPerson = MyComparator
                .comparing(Person::getAge)
                .thenComparing(Person::getName);
    }
}
