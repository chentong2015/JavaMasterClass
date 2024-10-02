package JavaIOResources.nio.filesystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

// zip是默认文件路径下的文件，同时也是一个独立的zip file system
// 1. Zip file is a file in the default file system
// 2. The content of zip is separate file system, a zip file system
//
// Zip file system uses "jar:file" scheme
// jar:file:/chentong/data/demo.jar
public class ZipFileSystemProvider {

    // zipPath = Paths.get("myData.zip") 这里创建处理的ZipFS下项目的主路径下面
    // FileSystem在打开resource之后，需要将其关闭
    // TODO. FileSystems.newFileSystem() 同一个时刻不能在同一个URL上创建两次
    private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
        // 提供File System的配置参数
        Map<String, String> env = new HashMap<>();
        env.put("capacity", "16G");
        env.put("blockSize", "4k");
        URI uriFs = URI.create("memory:///?name=logfs");
        FileSystem fs = FileSystems.newFileSystem(uriFs, env);

        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("create", "true");
        URI uri = new URI("jar:file", zipPath.toUri().getPath(), null);
        FileSystem zipFileSystem = FileSystems.newFileSystem(uri, providerProps);
        return zipFileSystem;
    }
}
