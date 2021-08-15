package JavaIOSerialization;

import JavaIOSerialization.model.Car;
import JavaIOSerialization.model.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 两种实现对象克隆的方式
// 1. 实现Cloneable接口并重写Object类中的clone()方法，方法默认是浅拷贝
//    Object.Clone(): this method performs a "shallow copy" of this object, not "deep copy" operation
// 2. 实现Serializable接口，通过对象的序列化和反序列化实现克隆，实现深度克隆
public class CloneObject {

    public static void main(String[] args) {
        try {
            Person person1 = new Person("chentong", 33, new Car("BMW"));
            Person person2 = cloneObject(person1);
            person2.getCar().setBrandName("New name");
            // 由于是深度copy，这里person修改的信息对person对象的内容没有任何的影响
            System.out.println(person1.getCar().getBrandName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: 基于序列化和反序列化实现的深度克隆
    // 通过泛型限定，可以检查出要克隆的对象是否支持序列化，这项检查是编译器完成的，不是在运行时抛出异常
    public static <T> T cloneObject(T object) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream serializeStream = new ObjectOutputStream(byteArrayOutputStream);
        serializeStream.writeObject(object);

        // 从序列化的ByteArrayOutputStream输出字节数组流中"反序列化"回原来的对象object
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream deserializeStream = new ObjectInputStream(byteArrayInputStream);
        return (T) deserializeStream.readObject();
    }
}
