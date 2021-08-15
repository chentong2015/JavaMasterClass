package JavaIOSerialization;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Read and write objects as a single unit 直接将对象作为一个单元读写，而非使用readInt(), readUTF()读取单个的field !!!
 * 1. ObjectInputStream extends InputStream implements ObjectInput
 * 2. ObjectOutputStream extends OutputStream implements ObjectOutput
 */
public class ObjectIOStreamSerialization implements Serializable {

    // 1. 声明一个常量，作为序列化的UID
    private static final long serialVersionUID = 1L;

    // 2. HashMap<>实现了Serializable接口
    private static Map<Integer, BaseSerializableObject> locations;

    public ObjectIOStreamSerialization() {
        locations = new HashMap<>();
    }

    // 3. 序列化: 将对象直接写到文件中 ObjectOutputStream >>
    private static void testObjectOutputStream() throws IOException {
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("JavaUnitTestExceptions.test.dat")))) {
            for (BaseSerializableObject objectModel : locations.values()) {
                locFile.writeObject(objectModel);
            }
        }
    }

    // 4. 反序列化：将序列化后的对象读取出来 << ObjectInputStream
    private static void testObjectInputStream() throws IOException {
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("JavaUnitTestExceptions.test.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    // readObject()可能抛出两种异常IOException, ClassNotFoundException 文件中读取不到指定类型的数据
                    BaseSerializableObject objectModel = (BaseSerializableObject) locFile.readObject();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (InvalidClassException ex) {   // 反序列化的异常
            ex.printStackTrace();
        } catch (ClassNotFoundException exception) {  // 反序列化读取文件的异常
            exception.printStackTrace();
        }
    }
}
