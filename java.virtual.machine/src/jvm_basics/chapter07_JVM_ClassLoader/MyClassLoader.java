package jvm_basics.chapter07_JVM_ClassLoader;

import java.io.IOException;
import java.io.InputStream;

// 从JVM的角度看，只有两种类加载器
// 1. 启动类加载器 Bootstrap ClassLoader, C++实现, 属于虚拟机的一部分
// 2. 其他所有类加载器, Javas实现, 独立于虚拟机外部, 都继承自抽象类ClassLoader

// JDK 9之前，三层类加载器
// 1. 启动类加载器：加载存放在<JAVA_HOME>\lib目录中的(基础核心)类库，通过名称进行特定识别 tools.jar
// 2. 扩展类加载器: 加载存放在<JAVA_HOME>\lib\ext目录中的类库
// 3. 应用程序加载器(系统类加载器): 加载用户类路径(Classpath)上的所有类库
// 4. 自定义类加载器
public class MyClassLoader extends ClassLoader {

    // 自定义的加载器
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

    // TODO: Parents Delegation Model "双亲委派模型"的源码实现
    // 1. 如果一个类加载器收到了类加载的请求，它首先会把这个请求委派给父类去完成，直到最顶层的"Bootstrap Class Loader"
    // 2. 如果父类加载器无法完成加载请求，子加载器才会尝试完成加载
    // 3. 该模型具备一种优先级的层次关系，自顶向下，同时确保一个类在不同的类加载环境下保证加载出同一个类
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded 检查请求类是否被加载过了
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                // try {
                //      if (parent != null) {
                //          c = parent.loadClass(name, false);
                //      } else {
                //          c = findBootstrapClassOrNull(name);
                //      }
                // } catch (ClassNotFoundException e) {
                //      抛出异常，说明父类加载器没有办法完成加载请求
                //      ClassNotFoundException thrown if class not found  from the non-null parent class loader
                // }
                if (c == null) {
                    // If still not found, then invoke findClass in order to find the class
                    // 使用本身的findClass方法来进行加载
                    long t1 = System.nanoTime();
                    c = findClass(name);
                }
            }
            if (resolve) resolveClass(c);
            return c;
        }
    }

    // 特列；线程上下文类加载器(JNDI服务使用)
    // 1. Parents Delegation Model 模型的逆向使用
    // 2. 一种父类加载器去请求子类加载器的行为
}

