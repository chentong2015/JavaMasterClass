package Streams.features;

import java.util.Optional;
import java.util.stream.Stream;

// mapMulti 输入一个Element元素，通过Consumer<R>隐射出0+个元素
public class StreamsMapMulti {

    public static void main(String[] args) {
        Stream.of(1, 2, 3)
                .mapMulti((number, consumer) -> {
                    consumer.accept(number);
                    consumer.accept(number * 10);
                })
                .forEach(System.out::println); // 1 10 2 20 3 30

        Stream<String> numberWords = Stream
                .of(0, 1, 2, 1, 0)
                .map(StreamsMapMulti::toWord)
                .mapMulti(Optional::ifPresent);
        System.out.println(numberWords.count()); // 4 四个有效结果
    }

    private static Optional<String> toWord(int number) {
        return switch (number) {
            case 0 -> Optional.of("zero");
            case 1 -> Optional.of("one");
            default -> Optional.empty();
        };
    }
}
