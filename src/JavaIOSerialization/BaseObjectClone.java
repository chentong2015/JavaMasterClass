package JavaIOSerialization;

import JavaIOSerialization.model.Car;
import JavaIOSerialization.model.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 两种实现对象克隆的方式
// 1. 实现Cloneable接口并重写Object类中的clone()方法，方法默认提供的是浅拷贝
//    Object.Clone(): this method performs a "shallow copy" of this object, not a "deep copy" operation
// 2. 实现Serializable接口，通过对象的序列化和反序列化实现克隆，实现深度克隆
public class BaseObjectClone {

    public static <T> T cloneObject(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    // 基于序列化和反序列化实现的克隆不仅仅是深度克隆，更重要的是通过泛型限定，
    // 可以检查出要克隆的对象是否支持序列化，这项检查是编译器完成的，不是在运行时抛出异常
    public static void main(String[] args) {
        try {
            Person p1 = new Person("Hao LUO", 33, new Car("BMW"));
            Person p2 = cloneObject(p1);   // 深度克隆
            p2.getCar().setBrandName("New name");
            // 修改克隆的Person对象p2关联的汽车对象的品牌属性
            // 原来的Person对象p1关联的汽车不会受到任何影响
            // 因为在克隆Person对象时其关联的汽车对象也被克隆了
            System.out.println(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
