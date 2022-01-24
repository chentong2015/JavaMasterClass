package JavaIO.IOPackage.text_stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaInputStreamReader {

    // TODO: 使用try-with-resources方式声明
    //  在关闭InputStreamReader时候，也会关闭它包含的input stream
    public void doChain(InputStream inputStream) throws IOException {
        int length;
        char[] charBuff = new char[128];
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            while ((length = reader.read(charBuff)) >= 0) {
                // do something with charBuff
                System.out.print(charBuff[0]);
            }
        }
    }
}
