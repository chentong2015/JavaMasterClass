package JavaStream.features;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.*;

// TODO. teeing 将数据同时交割两个Collector统计(处理), 再通过merger合并
public class StreamsTeeing {

    // 线程顺序地操作每个读取的Element元素
    //             Stream<T>
    //       ┌─────────┴─────────┐
    // downstream1          downstream2
    //      R1                  R2
    //       └─────────┬─────────┘
    //           merger(R1, R2)
    //                 R
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, 8, 5, 6);
        String result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue),
                        Collectors.averagingInt(Integer::intValue),
                        (sum, avg) -> "sum=" + sum + ", avg=" + avg));
        System.out.println(result); // sum=23, avg=4.6

        Range<String> range = Stream.of("A", "B", "C", "E")
                .collect(teeing(minBy(String::compareTo),
                        maxBy(String::compareTo),
                        Range::ofOptional))
                .orElseThrow(() -> new IllegalStateException("Non-empty stream was empty."));
        System.out.println(range); // Range [A, E]
    }

    public static class Range<T> {

        private final T min;
        private final T max;

        private Range(T min, T max) {
            this.min = requireNonNull(min);
            this.max = requireNonNull(max);
        }

        public static <T> Range<T> of(T min, T max) {
            return new Range<T>(min, max);
        }

        public static <T> Optional<Range<T>> ofOptional(Optional<T> min, Optional<T> max) {
            if (min.isEmpty() || max.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(new Range<>(min.get(), max.get()));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Range<?> range = (Range<?>) o;
            return min.equals(range.min) && max.equals(range.max);
        }

        @Override
        public int hashCode() {
            return Objects.hash(min, max);
        }

        @Override
        public String toString() {
            return "Range [" + min + ", " + max + ']';
        }
    }
}