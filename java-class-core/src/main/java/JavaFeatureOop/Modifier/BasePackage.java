package JavaFeatureOop.Modifier;

import org.w3c.dom.Node;
import java.awt.*;

// 不能再引入同名的类型
// import org.w3c.dom.Node;

// TODO. 静态引入类型(不考虑创建类型的实例)，直接调用类型的静态方法
// import static com.example.main.DemoClass;

// Package: namespaces 避免名称的冲突
// 1. 集合一组相关(功能)的类型或者是接口
// 2. 同一个包中的类型之间是可以直接访问的，但是对外包存在访问约束
public class BasePackage extends Frame {

    // 1. 只在定义类型的内部可见
    private String privateStr;

    // 2. 对外部的包只有继承的子类中可见(无论继承类是否在同一个包中)
    protected String protectedStr;

    // 4. 在任何地方，包内或是包外都能被可见
    public String publicStr;

    public void testPackage() {
        // 一个使用import; 同时避开直接注明 !!
        Node node = null;

        // 可以使用import来引入, 或者是直接注明, 没有使用都需要完整的注明 !!
        org.w3c.dom.Node anotherNode = null;

        // Series series = new Series(); 使用声明的类型，需要import Series
        // Series.factor(100);
    }
}
