package JavaIO.NIOPackage.fileTree;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 使用Files.walk遍历指定Path下的所有Stream Path
// Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options)
public class NioFilesWalkDemo {

    // TODO. 使用try-with-resource关闭读取出来的path stream流, 自动调用close()方法
    public static void main(String[] args) throws IOException, URISyntaxException {
        URL resource = NioFilesWalkDemo.class.getClassLoader().getResource("folder_name");
        assert resource != null;
        Path folder = Paths.get(resource.toURI());

        try (Stream<Path> pathList = Files.walk(folder)
                .filter(Files::isRegularFile)
                .map(Path::getFileName).sorted()) {
            for (Path path : pathList.collect(Collectors.toList())) {
                System.out.println(path.toString());
            }
        }
    }

    /**
     * 1. 遍历文件夹，从文件夹的所有文件中找到需要的文件
     * 2. 复制文件夹，需要将文件夹中的所有文件全部拷贝一份
     * 3. 以下重载的方法支持访问目录的最大层级，以及FileVisitOption.FOLLOW_LINKS是否追踪文件链接
     * Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor)
     */
    private void testWalkFileTree() {
        try {
            Path rootFolder = FileSystems.getDefault().getPath("WorkFolder");
            Files.walkFileTree(rootFolder, new MySimpleFileTreeVisitor());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 使用Path类型的两个方法实现File Tree的完整拷贝, 相对的路径始终保持一致
     * sourceRoot = "FileTree\Dir1"
     * sourcePath = "FileTree\Dir1\Dir2\Test.txt"
     * targetRoot = "FileTree\Dir3\"
     * relativePath = sourceRoot.relative(sourcePath) => "\Dir2\Test.txt" 相对于root路径的相对路径
     * targetRoot.resolve(relativePath) => "FileTree\Dir3\Dir2\Test.txt" 在目标root路径的基础上补充相对路径
     */
    private void testCopyFileTree() {
        Path sourceDir = FileSystems.getDefault().getPath("WorkFolder");
        Path targetDir = FileSystems.getDefault().getPath("WorkFolderCopy"); // 结果的总文件夹不能已经存在 !!
        try {
            Files.walkFileTree(sourceDir, new CopyFileTreeVisitor(sourceDir, targetDir));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
