package JavaLambdaExpression.methodReference;

import java.util.function.BinaryOperator;

public class BaseBinaryOperator {

    public void magic(BinaryOperator<Long> lambda) {
        lambda.apply(3L, 4L);
    }

    // 使用正确的Lambda表达式来符合@FunctionalInterface的定义
    public void testBinaryOperator() {
        // magic((Integer a, Integer b) -> (Long) a + b);

        // magic((c, m) -> {
        //     long c = 4;
        //     return c + m;
        // });

        magic((b, w) -> (long) w.intValue());
    }
}
