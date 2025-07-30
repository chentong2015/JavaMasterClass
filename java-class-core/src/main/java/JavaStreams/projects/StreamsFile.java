package JavaStreams.projects;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class StreamsFile {

    // 测试功能：读取指定路径下的文件，将其中的单词按字母排序后的结果进行分组，打印超过指定size的组中单词
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(StreamsFile::alphabetize))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    // 一般使用try Stream来读取文件
    public void testStreamFiles(String filepath) {
        try (Stream<String> stream = Files.lines(Paths.get(filepath), StandardCharsets.UTF_8)) {
            List<String> list = stream.toList();
            System.out.println(list.get(0));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 对String字符串进行字母排序
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
