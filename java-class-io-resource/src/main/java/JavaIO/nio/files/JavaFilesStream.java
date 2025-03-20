package JavaIO.nio.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO. Files创建IO Stream的静态方法
public class JavaFilesStream {

    public void testFilesInputOutputStream(Path path) throws IOException {
        // Opens a file, returning an input stream to read from the file.
        // The stream will not be buffered
        InputStream inputStream = Files.newInputStream(path);

        // Opens or creates a file, returning an output stream that may be used to write bytes to the file.
        OutputStream outputStream = Files.newOutputStream(path);
    }
}
