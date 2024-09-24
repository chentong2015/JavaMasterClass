package JavaIOResources.nio.filesystem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;

public abstract class AbstractResourceHelper {

    private static final String DELIMITER = "/";
    private static final String MESSAGE = "cannot read the resource {} in classpath";

    protected String loadResource(final String classpath) throws IOException, URISyntaxException {
        URL resource = this.getClass().getClassLoader().getResource(classpath);
        if (resource != null) {
            URI uri = resource.toURI();
            try {
                return Files.readString(Paths.get(uri));
            } catch (Exception e) {
                System.out.println("aa");
            }

            try (FileSystem zipFs = FileSystems.newFileSystem(uri, Collections.singletonMap("create", "true"))) {
                Path path = zipFs.getPath(DELIMITER + classpath);

                Files.readAllBytes(path);

                return Files.readString(path);
            } catch (Exception e) {
                System.out.println("bb");
            }

            try (InputStream is = resource.openStream()) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buff = new byte[1024 * 8];
                while (is.available() > 0) {
                    int read = is.read(buff);
                    out.write(buff, 0, read);
                }
                return out.toString(StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println("cc");
            }
        }
        return null;
    }
}
