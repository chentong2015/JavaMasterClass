package JavaInputOutput;

import JavaInputOutput.Model.IndexRecord;
import JavaInputOutput.Model.SerializableObjectModel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * RandomAccessFile:
 * 1. Skip any bytes 随机访问文件的指定位置, 而非按指定顺序从开始到结尾
 * 2. 并不是将所有的文件的内容都读取到内存中，而是根据需求，通过偏移量从文件中读取指定的内容(到内存)
 * 3. file pointer is a offset where the next read or write will start from
 * 4. 文件偏移指针zero-based从0开始，在读写之后会往后移动
 * 5. Can not read & write objects !!!
 * 6. A closed random access file cannot perform input or output operations and cannot be reopened.
 */
public class BaseRandomAccessFile {

    private static Map<Integer, SerializableObjectModel> objects = new HashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();

    /**
     * 配置一个标准的Random Access File
     * 1. 第一段4个字节存放序列化对象数目
     * 2. 第二段4个字节存放对象存储开始的偏移量
     * 3. Contains the index (1692 bytes long), from byte 8 to byte 1699 ????
     * 4. 存储对象序列化的数据data
     */
    private static void testWriteRandomAccessFile() throws IOException {
        // mode: read + write + synchronously 同步操作  ==>  异步模式可使得多个线程同时可访问，造成风险
        try (RandomAccessFile rao = new RandomAccessFile("testRandom.dat", "rwd")) {
            rao.writeInt(objects.size());

            int indexSize = objects.size() * 3 * Integer.BYTES; // 数据的数目 * 3个Integer是Index记录的数据站占的大小 !!!
            int objectStart = (int) (rao.getFilePointer() + indexSize + Integer.BYTES); // BYTES = 4 bytes
            rao.writeInt(objectStart);

            long startPoint = rao.getFilePointer(); // 记录下，在写入具体对象数据前的位置

            int offsetPointer = objectStart;
            rao.seek(offsetPointer); // Sets the file-pointer offset, measured from the beginning of this file, at which the next read or write occurs

            for (SerializableObjectModel objectModel : objects.values()) {
                rao.writeInt(objectModel.getID());
                rao.writeUTF(objectModel.getName());

                // 每序列化一个对象的数据，记录起始位置和长度
                IndexRecord record = new IndexRecord(offsetPointer, (int) (rao.getFilePointer() - offsetPointer));
                index.put(objectModel.getID(), record);

                offsetPointer = (int) rao.getFilePointer(); // 更新指针的位置
            }

            // 将记录的Index数据写入: 每记录一组数据，占据3个Integer的长度
            rao.seek(startPoint);
            for (Integer id : index.keySet()) {
                rao.writeInt(id);
                rao.writeInt(index.get(id).getStartByte());
                rao.writeInt(index.get(id).getLength());
            }
        }
    }

    private static void testReadRandomAccessFile() throws IOException {
        try (RandomAccessFile rao = new RandomAccessFile("test.dat", "rwd");) {
            int count = rao.readInt();
            long startPoint = rao.readInt();
            while (rao.getFilePointer() < startPoint) {
                int objectID = rao.readInt();
                int objectStart = rao.readInt();
                int objectLength = rao.readInt();
                IndexRecord record = new IndexRecord(objectStart, objectLength);
            }
        }
    }
}
