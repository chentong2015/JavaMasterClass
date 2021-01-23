package JavaInputOutput.JavaNIOPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaTempFileAndFileStores {

    /**
     * Create temporary file in OS's default temporary file directory 创建程序需要的默认文件
     * 在不同的OS，默认的目录不同
     * C:\Users\Username\AppData\Local\Temp\myapp15961596656467916.appext Java会自动生成临时ID到文件的名称中 !!!
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

    /**
     * Hard Disk 硬盘 / Drives 驱动盘: each drive or volume is a fileStore
     */
    private void testFileStore() {

    }

}
