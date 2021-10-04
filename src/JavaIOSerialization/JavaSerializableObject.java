package JavaIOSerialization;

import java.io.Serializable;

// Serialization 序列化：对象和字节流之间的转换
// 1. 将数据结构或者Object转换成可以被存储的格式(Stream流)的过程被称为是序列化
//    When write an object to a file, it has to be translated to a format that can be stored to a file, and read it later
// 2. 类型必须是可以序列化的 implements Serializable
//    Serializable接口没有方法，目的只是为了给JVM一个标识，标识这个类型"可能"被序列化然后再读取处理
//    需要使用一个private long serialVersionUID;
//        如果不是自定义，将会被赋予一个默认值:
//        编译器会根据类型包含的fields和其类型details生成，在类型变动之后，当反序列化时runtime验证serialVersionUID可能会出现不一致
//        在不同的compiler之间可能存在差异: Standard Oracle Compiler for Desktop APP & Android 不同编译器序列化效果不同
// 3. 类型中的所有的field都必须是可以序列化的
// 4. 将同一个对象序列化两次到一个文件中，文件中之后存储"一次"对象的序列化结果
public class JavaSerializableObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String Name;

    public JavaSerializableObject(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
