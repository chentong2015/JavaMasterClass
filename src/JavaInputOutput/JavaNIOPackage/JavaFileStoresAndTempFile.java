package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaFileStoresAndTempFile {

    /**
     * Each drive or volume is a fileStore 一个硬盘，就是一个存储数据的载体
     * Volume盘(逻辑概念), Windows的标准叫法  ==> 等效于Drive盘(槽), C drive, C:
     * Partition分区(物理概念), 在这个载体上分成几大部分，每一部分就是一个分区
     * OS has space : 371 G
     * DATA has space : 558 G
     */
    private void testGetFileStores() throws IOException {
        // FileSystems.getDefault() 当一个系统拥有多个文件系统的时候, 返回默认的File System Object
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores) {
            System.out.println(store); // OS (C:) 全称
            String nameStore = store.name();
            long spaceStore = store.getTotalSpace() / (1024 * 1024 * 1024); // bits -> G
        }

        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths) {
            System.out.println(path); // C:\ 最顶级的目录
        }
    }

    /**
     * Create temporary file in OS's default temporary file directory 创建程序需要的默认文件
     * 在不同的OS，默认的目录不同
     * C:\Users\Username\AppData\Local\Temp\myapp15961596656467916.appext Java会自动生成临时ID到文件的名称中
     * C:\Users\CHEN%20Tong\AppData\Local\Temp\myApp_f6a44001-3674-4140-8521-36e30af51b9a.log 实际生成临时日志文件
     * 1. 上面路径始终和File System相关联，可通过提供(Path dir,,,)参数创建在不同位置
     * 2. 也可以直接创建临时目录 Files.createTempDirectory()
     */
    private void createTemporaryFileInOS() {
        try {
            Path tempFile = Files.createTempFile("myapp", ".appext");
            Path absoluteFilepath = tempFile.toAbsolutePath();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
