package JavaIOResources.nio.filesystem;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;

/**
 * Each drive or volume is a fileStore 一个硬盘，就是一个存储数据的载体
 * Volume盘(逻辑概念), Windows的标准叫法  ==> 等效于Drive盘(槽), C drive, C:
 * Partition分区(物理概念), 在这个载体上分成几大部分，每一部分就是一个分区
 * OS has space : 371 G
 * DATA has space : 558 G
 */
public class JavaFileStoresDrive {

    private void testGetFileStores() throws IOException {
        // FileSystems.getDefault()
        // 当一个系统拥有多个文件系统的时候, 返回默认的File System Object
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores) {
            System.out.println(store); // OS (C:) 全称
            String nameStore = store.name();
            long spaceStore = store.getTotalSpace() / (1024 * 1024 * 1024); // bits -> G
        }


    }
}
