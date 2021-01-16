package JavaInputOutput;

import JavaInputOutput.Model.SerializableObjectModel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Serialization 序列化：对象和字节流之间的转换
 * 1. 将数据结构或者Object转换成可以被存储的格式(Stream流)的过程被称为是序列化 !!!!
 * --- When write an object to a file, it has to be translated to a format that can be stored to a file, and read it later
 * 2. 类型必须是可以序列化的 implements Serializable
 * --- 2.1 Serializable接口没有方法，目的只是为了给JVM一个标识，标识这个类型"可能"被序列化然后再读取处理
 * --- 2.2 需要使用一个private long filed => serialVersionUID;
 * --- 2.3 如果不是自定义，将会被赋予一个默认值:
 * -------- 2.1.1 编译器会根据类型包含的fields和其类型details生成，在类型变动之后，当反序列化时runtime验证serialVersionUID可能会出现不一致, InvalidClassException
 * -------- 2.2.2 在不同的compiler之间可能存在差异: Standard Oracle Compiler for Desktop APP & Android Project 不同编译器
 * 3. 类型中的所有的field都必须是可以序列化的
 * ---------------------------------------
 * Read and write objects as a single unit 直接将对象作为一个单元读写，而非使用readInt(), readUTF()读取单个的field !!!
 * 1. ObjectInputStream extends InputStream implements ObjectInput
 * 2. ObjectOutputStream extends OutputStream implements ObjectOutput
 */
public class BaseObjectIOStreamSerialization implements Serializable {

    // 1. 声明一个常量，作为序列化的UID
    private static final long serialVersionUID = 1L;

    // 2. HashMap<>实现了Serializable接口
    private static Map<Integer, SerializableObjectModel> locations;

    public BaseObjectIOStreamSerialization() {
        locations = new HashMap<>();
    }

    // 3. 序列化: 将对象直接写到文件中
    private static void testObjectOutputStream() throws IOException {
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("test.dat")))) {
            for (SerializableObjectModel objectModel : locations.values()) {
                locFile.writeObject(objectModel);
            }
        }
    }

    // 4. 反序列化：将序列化后的对象读取出来
    private static void testObjectInputStream() throws IOException {
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("test.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    // readObject() 可能抛出两种异常 IOException, ClassNotFoundException ==> 从文件中读取不到指定类型的数据 !!
                    // 将读取的数据 强制转换成指定的类型
                    SerializableObjectModel objectModel = (SerializableObjectModel) locFile.readObject();
                } catch (EOFException | ClassNotFoundException e) {
                    eof = true;
                }
            }
        }
    }

}
