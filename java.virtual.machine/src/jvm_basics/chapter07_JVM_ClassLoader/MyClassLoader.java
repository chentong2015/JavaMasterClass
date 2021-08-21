package jvm_basics.chapter07_JVM_ClassLoader;

import java.io.IOException;
import java.io.InputStream;

// 从JVM的角度看，只有两种类加载器
// 1. 启动类加载器   : Bootstrap ClassLoader(C++实现), 属于虚拟机的一部分 --> 显示出来是null
// 2. 其他所有类加载器: 继承自抽象类ClassLoader, 由Java实现, 独立于虚拟机外部

// JDK 9之前，三层类加载器
// 1. 启动类加载器：加载存放在<JAVA_HOME>\lib目录中的(基础核心)类库，通过特定名称识别rt.jar, tools.jar
// 2. 扩展类加载器: 加载存放在<JAVA_HOME>\lib\ext目录中的类库
// 3. 应用程序加载器(系统类加载器): 加载用户类路径(Classpath)上的所有类库
// 4. 自定义类加载器
public class MyClassLoader extends ClassLoader {

    // TODO: "Class Loader类加载器"：通过一个类的全限定命来获取描述该类的二进制字节流
    // 类本身和它的类加载器共同决定了在其虚拟机中的唯一性
    // "类相等"是指由同一个类加载器的前提下才有意义: equals(), isAssignableFrom(), isInstance()方法返回的结果必须一致
    public void testClassLoader() {
        System.out.println(String.class.getClassLoader()); // 拿到指定类型的类加载器
        // classLoader.getClass().getName();
        // sum.misc.Launcher$AppClassLoader   AppClassLoader是声明在启动器Launcher类中的静态内部类

        ClassLoader classLoader = ClassLoader.getSystemClassLoader(); // 获取系统默认的类加载器AppClassLoader
        classLoader.getParent(); // 拿到它的上级类加载器
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            // 从提供的路径中解析并获取出class文件的二级制流
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = getClass().getResourceAsStream(fileName);
            if (is == null) return super.loadClass(name);
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
}

