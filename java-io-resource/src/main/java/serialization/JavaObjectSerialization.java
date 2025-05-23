package serialization;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Read and write objects as a single unit
// 直接将对象作为一个单元读写，而非使用readInt(), readUTF()读取单个的field
// 1. ObjectInputStream extends InputStream implements ObjectInput
// 2. ObjectOutputStream extends OutputStream implements ObjectOutput
public class JavaObjectSerialization implements Serializable {

    // 1. 声明一个常量，作为序列化的UID
    private static final long serialVersionUID = 1L;

    // 2. HashMap<>实现了Serializable接口
    private static Map<Integer, ObjectSerializable> locations;

    public JavaObjectSerialization() {
        locations = new HashMap<>();
    }

    // 3. 序列化: 将对象直接写到文件中 ObjectOutputStream.writeObject()
    private static void testObjectOutputStream() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("JavaUnitTestExceptions.test.dat");
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(outputStream))) {
            for (ObjectSerializable objectModel : locations.values()) {
                locFile.writeObject(objectModel);
            }
        }
    }

    // 4. 反序列化：将序列化后的对象读取出来 ObjectInputStream.readObject()
    private static void testObjectInputStream() throws Exception {
        FileInputStream inputStream = new FileInputStream("JavaUnitTestExceptions.test.dat");
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(inputStream))) {
            boolean eof = false;
            while (!eof) {
                try {
                    // TODO. 反序列化机制是一个隐藏的构造器，可能造成对象关系的破坏
                    ObjectSerializable objectModel = (ObjectSerializable) locFile.readObject();
                } catch (EOFException e) {
                    eof = true;
                }
            }
        }
    }
}
