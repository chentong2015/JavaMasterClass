package JavaBasic.JavaCharEncoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaCharBuffer {

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
