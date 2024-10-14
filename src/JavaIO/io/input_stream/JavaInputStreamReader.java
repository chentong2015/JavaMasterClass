package JavaIO.io.input_stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public class JavaInputStreamReader {


    public void testBufferedReader(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        // 直接通过InputStreamReader来从Input Stream中读取字节
        // inputStreamReader.read()

        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            Stream<String> streams = bufferedReader.lines(); // .skip(1)
            List<String> lines = streams.toList();
            System.out.println(lines.get(0));
            System.out.println(lines.get(1));
        }
    }

    // InputStreamReader从指定的inputStream中读取指定的字节长度
    public void doChain(InputStream inputStream) throws IOException {
        char[] charBuff = new char[128];
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            while (reader.read(charBuff) >= 0) {
                // do something with charBuff
                System.out.print(charBuff[0]);
            }
        }
    }
}
