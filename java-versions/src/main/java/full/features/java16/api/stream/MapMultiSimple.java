package full.features.java16.api.stream;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MapMultiSimple {

    public void mapMulti() {
        Stream.of(1, 2, 3, 4)
                .mapMulti((number, downstream) -> downstream.accept(number))
                .forEach(System.out::print);

        List<Integer> numbers = Stream.of(1, 2, 3, 4)
                .<Integer>mapMulti((number, downstream) -> downstream.accept(number))
                .collect(toList());
    }

}
