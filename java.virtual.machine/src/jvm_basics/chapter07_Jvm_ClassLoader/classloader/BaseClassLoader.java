package jvm_basics.chapter07_Jvm_ClassLoader.classloader;

// 从JVM的角度看，只有两种类加载器
// 1. 启动类加载器: Bootstrap ClassLoader(C++实现), 属于虚拟机的一部分 --> 显示出来是null
// 2. 其他所有类加载器: 继承自抽象类ClassLoader, 由Java实现, 独立于虚拟机外部

// JDK 9之前，三层类加载器
// 1. 启动类加载器: 加载存放在<JAVA_HOME>\lib目录中的(基础核心)类库，通过特定名称识别rt.jar, tools.jar
// 2. 扩展类加载器: 加载存放在<JAVA_HOME>\lib\ext目录中的类库
// 3. 应用程序加载器: 也叫系统类加载器，加载用户类路径(Classpath)上的所有类库
// 4. 自定义类加载器
public class BaseClassLoader {

    // "Class Loader类加载器"：通过一个类的全限定命来获取描述该类的二进制字节流
    // 1. MyClass.class类之前还必须加载它的父类.class
    // 2. TODO: 类本身和它的类加载器共同决定了在其虚拟机中的唯一性, 判断同名的class类型在同一个JVM下能否共存
    // 3. "类相等"是指由同一个类加载器的前提下才有意义: equals(), isAssignableFrom(), isInstance()方法返回的结果必须一致
    public void testClassLoader() {
        System.out.println(String.class.getClassLoader()); // 拿到指定类型的类加载器
        // classLoader.getClass().getName();
        // sum.misc.Launcher$AppClassLoader   AppClassLoader是声明在启动器Launcher类中的静态内部类

        // 获取系统默认的类加载器$AppClassLoader
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        classLoader.getParent(); // 返回上级类加载器
    }
}
