package JavaIOResources.IOPackage.file_reader_writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaInputStreamReader {

    public void testBufferedReader(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            Stream<String> streams = bufferedReader.lines(); // .skip(1)
            List<String> lines = streams.collect(Collectors.toList());
            System.out.println(lines.get(0));
            System.out.println(lines.get(1));
        }
    }
}
