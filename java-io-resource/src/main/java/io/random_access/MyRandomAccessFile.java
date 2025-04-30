package io.random_access;

import serialization.ObjectSerializable;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyRandomAccessFile {

    private static RandomAccessFile rao;
    private static Map<Integer, ObjectSerializable> objects = new HashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("file.dat", "rwd");) {
            byte[] readBytes = new byte[10];
            file.read(readBytes);
            // 将读取的10个字节数据转换成字符串
            String readStr = new String(readBytes);

            // 读完指定的bytes之后，读取int(一个byte)
            long readInt = file.readInt();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 配置一个标准的Random Access File
     * 1. 第一段4个字节存放序列化对象的数目
     * 2. 第二段4个字节存放对象开始存储的的偏移量 (data开始存储的具体位置)
     * 3. Contains the index record 记录的偏移信息
     * 4. Contains all the objects data 实际序列化数据
     */
    private static void testWriteRandomAccessFile() {
        // mode: read + write + synchronously 同步操作  ==>  异步模式可使得多个线程同时可访问，造成风险
        try {
            rao = new RandomAccessFile("testRandom.dat", "rwd");
            rao.writeInt(objects.size());

            // 数据数目 * 3个Integer (Index记录的数据站占的大小)
            // Integer.BYTES = 4 bytes: 多添加的4个bytes是用来存储objectStartPoint值 !!
            int objectStartPoint = (int) (rao.getFilePointer() + objects.size() * 3 * Integer.BYTES + Integer.BYTES);
            rao.writeInt(objectStartPoint);

            // 上面存储完两个int数据之后，记录存放Index record的位置点
            long startPoint = rao.getFilePointer();

            // 找到存储对象的实际点：根据上面所计算出移动的偏移位置 !!
            int offsetPointer = objectStartPoint;
            rao.seek(offsetPointer);
            for (ObjectSerializable objectModel : objects.values()) {
                // 依次序列化fields !!
                rao.writeInt(objectModel.getID());

                // 根据不同的string长度，所占的大小不同
                // 实际存 link to the string + string itself
                rao.writeUTF(objectModel.getName());

                // 每序列化一个对象的数据，记录起始位置和长度
                IndexRecord record = new IndexRecord(offsetPointer, (int) (rao.getFilePointer() - offsetPointer));
                index.put(objectModel.getID(), record);
                offsetPointer = (int) rao.getFilePointer();
            }
            // 将记录的Index数据写入: 每记录一组数据，占据3个Integer的长度
            rao.seek(startPoint);
            for (Integer id : index.keySet()) {
                rao.writeInt(id);
                rao.writeInt(index.get(id).getStartByte());
                rao.writeInt(index.get(id).getLength());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void testReadRandomAccessFile() throws IOException {
        try {
            rao = new RandomAccessFile("JavaUnitTestExceptions.test.dat", "rwd");
            int count = rao.readInt();            // 序列化对象的数目
            long startObjectPoint = rao.readInt();
            while (rao.getFilePointer() < startObjectPoint) { // 拿到具体的所有index record, 在存储所有对象序列化数据之前 !!
                int objectID = rao.readInt();
                int objectStart = rao.readInt();  // 对象的存储起点位置
                int objectLength = rao.readInt(); // 拿到对象实际存储长度
                IndexRecord record = new IndexRecord(objectStart, objectLength);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // 使用objectID来获取序列化的数据的偏移量，返回查找的对象
    private ObjectSerializable getObjectModel(int objectID) throws IOException {
        IndexRecord record = index.get(objectID);
        rao.seek(record.getStartByte());
        int id = rao.readInt();
        String name = rao.readUTF(); // Reads the link to string, and then reads the string value !!
        return new ObjectSerializable(id, name);
    }

    // A closed random access file cannot perform input or output operations and cannot be reopened.
    private static void closeRandomAccessFile() throws IOException {
        rao.close();
    }
}
