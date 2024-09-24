package JavaIOResources.nio.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbstractPathUtils {

    /**
     * Removes the path traversing elements from the given path (../), and gets the canonical path.
     * @param path to be cleaned and canonicalized.
     * @return clean and canonicalized path.
     * @throws IOException when system fails during canonical path construction
     *      (canonical pathname may require filesystem queries)
     */
    public static String cleanAndCanonicalize(String path) throws IOException {
        if (path == null) {
            path = "";
        }
        path = Stream.of(path.split("[/\\\\]"))
            .filter(str -> !"..".equals(str))
            .collect(Collectors.joining(File.separator));
        return new File(path).getCanonicalPath();
    }

    private AbstractPathUtils() {}
}
