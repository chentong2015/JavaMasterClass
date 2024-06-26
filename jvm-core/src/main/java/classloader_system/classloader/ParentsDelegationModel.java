package classloader_system.classloader;

// TODO: Parents Delegation Model "双亲委派机制"的源码实现
// 如果一个类加载器收到了类加载的请求，首先会判断这个类是否加载过，如果没有则执行以下流程
// 1. 它首先会把这个请求委派给父类去完成，直到最顶层的"Bootstrap ClassLoader"
// 2. 如果父类加载器无法完成加载请求(找不到类型)，子加载器才会尝试完成加载
// 3. 该模型具备一种优先级的层次关系，自顶向下，同时确保一个类在不同的类加载环境下保证加载出同一个类
//
// 为什么会设计该机制 ?
// 1. 避免类的重新加载：上级类加载器加载过的类型，没必要下级再次加载
// 2. TODO: 沙箱安全机制：避免java核心API类库中的类型(包名类名必须一致)被用户篡改
//          比如不能自定义java.lang.String类型由交给AppClassLoader加载
// 3. 优化加载的时间：类只会被加载一次，对于大多自定义的类型，直接在AppClassLoader中判断即可，而不是从上往下 !!
public class ParentsDelegationModel {

    // ClassLoader中定义的loadClass()方法源码: 符合"双亲委派机制"
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // synchronized (getClassLoadingLock(name)) {
        //     // First, check if the class has already been loaded
        //     检查请求类是否被加载过了
        //     Class<?> c = findLoadedClass(name);
        //     if (c == null) {
        //         long t0 = System.nanoTime();
        //         try {
        //              if (parent != null) {
        //                  c = parent.loadClass(name, false);
        //              } else {
        //                  c = findBootstrapClassOrNull(name);
        //              }
        //         } catch (ClassNotFoundException e) {
        //              抛出异常，说明父类加载器没有办法完成加载请求
        //              ClassNotFoundException thrown if class not found from the non-null parent class loader
        //         }
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

    // TODO: 打破Parents Delegation Model机制
    // 避免向上委托，或者逆向使用(父类加载器去请求子类加载器的行为)
    // 1. JNDI服务: 对资源进行查找和集中管理
    //    使用Thread Context ClassLoader来加载指定classpath下JDBI服务提供的接口代码
    // 2. Tomcat加载机制: 借助WebAppClassLoader来加载自己目录下的class文件
    protected Class<?> loadClassPlus(String name, boolean resolve) throws ClassNotFoundException {
        // synchronized (getClassLoadingLock(name)) {
        //     // First, check if the class has already been loaded 检查请求类是否被加载过了
        //     Class<?> c = findLoadedClass(name);
        //     if (c == null) {
        //         long t0 = System.nanoTime();
        //         ** 删除"双亲向上委托"，打破加载机制 ==> 只能打破非Java核心API中的类，否则受到"沙箱安全机制"的影响 **
        //         if (c == null) {
        //             // If still not found, then invoke findClass in order to find the class
        //             // 使用本身的findClass方法来进行加载
        //             long t1 = System.nanoTime();
        //
        //             ** 规避掉由于"沙箱安全机制"的影响，导致的不能加载自定义的类型 **
        //             if(name.startWith("com.ctong.main")) {
        //                 c = findClass(name);
        //             } else {
        //                 c = this.getParent().loadClass(name);
        //             }
        //         }
        //     }
        //     if (resolve) resolveClass(c);
        //     return c;
        // }
        return null;
    }
}
