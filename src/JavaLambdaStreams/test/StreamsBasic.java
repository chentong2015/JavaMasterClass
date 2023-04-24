package JavaLambdaStreams.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class StreamsBasic {

    // 使用一行代码统计一组数据中满足条件的item的数目
    private long countNumber(int... array) {
        return (array == null) ? 0 : Arrays.stream(array).filter(i -> i == 9).count();
    }

    // 获取一组数据中的Top3
    public void testIntermediateOperations() {
        Stream<String> s = Stream.of("m", "k", "c", "t").sorted().limit(3);
        System.out.println(s);
    }

    private static void testStreamOperations() {
        Stream<String> ioNumberStream = Stream.of("I24", "O90", "A12");
        Stream<String> inNumberStream = Stream.of("N40", "I26", "O23");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        long total = concatStream.distinct()
                .peek(System.out::println)
                .count();
        System.out.println(total);
    }

    // 一般使用try Stream来读取文件
    public void testStreamFiles(String filepath) {
        try (Stream<String> stream = Files.lines(Paths.get(filepath), StandardCharsets.UTF_8)) {
            System.out.println(stream.toList().get(0));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 测试功能：读取指定路径下的文件，将其中的单词按字母排序后的结果进行分组，打印超过指定size的组中单词
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(StreamsBasic::alphabetize))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    // 对String字符串进行字母排序
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
