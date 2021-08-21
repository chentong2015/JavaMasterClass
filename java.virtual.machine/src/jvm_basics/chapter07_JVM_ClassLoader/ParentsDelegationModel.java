package jvm_basics.chapter07_JVM_ClassLoader;

// TODO: Parents Delegation Model "双亲委派机制"的源码实现
// 如果一个类加载器收到了类加载的请求，首先会判断这个类是否加载过，如果没有则执行以下流程
// 1. 它首先会把这个请求委派给父类去完成，直到最顶层的"Bootstrap Class Loader"
// 2. 如果父类加载器无法完成加载请求(找不到类型)，子加载器才会尝试完成加载
// 3. 该模型具备一种优先级的层次关系，自顶向下，同时确保一个类在不同的类加载环境下保证加载出同一个类
// 设计原因 ?
// 1. 避免类的重新加载：上级类加载器加载过的类型，没必要下级再次加载
// 2. 沙箱安全机制   ：避免java核心API类库中的类型(包名类名必须一致)被用户篡改，比如不能自定义java.lang.String类型由交给AppClassLoader加载
// 3. 时间快速      ：类只会被加载一次，对于大多自定义的类型，直接在AppClassLoader中判断即可，而不是从上往下 !!
public class ParentsDelegationModel {

    // ClassLoader中定义的loadClass()方法源码: 符合"双亲委派机制"
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // synchronized (getClassLoadingLock(name)) {
        //     // First, check if the class has already been loaded 检查请求类是否被加载过了
        //     Class<?> c = findLoadedClass(name);
        //     if (c == null) {
        //         long t0 = System.nanoTime();
        //            try {
        //                 if (parent != null) {
        //                     c = parent.loadClass(name, false);
        //                 } else {
        //                     c = findBootstrapClassOrNull(name);
        //                 }
        //            } catch (ClassNotFoundException e) {
        //                 抛出异常，说明父类加载器没有办法完成加载请求
        //                 ClassNotFoundException thrown if class not found  from the non-null parent class loader
        //            }
        //         if (c == null) {
        //             // If still not found, then invoke findClass in order to find the class
        //             // 使用本身的findClass方法来进行加载
        //             long t1 = System.nanoTime();
        //             c = findClass(name);
        //         }
        //     }
        //     if (resolve) resolveClass(c);
        //     return c;
        // }
        return null;
    }

    // 特列；线程上下文类加载器(JNDI服务使用)
    // 1. Parents Delegation Model 模型的逆向使用
    // 2. 一种父类加载器去请求子类加载器的行为
    // 3. Tomcat加载机制，打破Parents Delegation Model机制
}
