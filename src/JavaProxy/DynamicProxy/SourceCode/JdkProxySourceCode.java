package JavaProxy.DynamicProxy.SourceCode;

// https://cloud.tencent.com/developer/article/1461796
// https://docs.oracle.com/javase/7/docs/technotes/guides/reflection/proxy.html
// https://www.huaweicloud.com/articles/696efe7865fb093d4217487b29fc2506.html
// https://bbs.huaweicloud.com/blogs/detail/191522
// https://bbs.huaweicloud.com/blogs/215606
// 动态代理: 动态代理类是代理类在程序"运行时"创建的代理模式，动态生成
public class JdkProxySourceCode {

    // import java.lang.reflect.Proxy;  动态代理类技术核心 Proxy类
    // import java.lang.reflect.InvocationHandler; 被动态代理类回调的接口
    // InvocationHandler is the interface implemented by the invocation handler of a proxy instance

    // 生成动态代理类,返回class实例代表一个class文件  ==> 借助Java反射来实现 !!
    // loader – the class loader to define the proxy class
    // interfaces – the list of interfaces for the proxy class to implement
    // Returns: a proxy class that is defined in the specified class loader and that implements the specified interfaces
    //
    // public static Class<?> getProxyClass(ClassLoader loader,  Class<?>... interfaces)
    //        throws IllegalArgumentException {
    //        Class<?> caller = System.getSecurityManager() == null ? null : Reflection.getCallerClass();
    //        return getProxyConstructor(caller, loader, interfaces).getDeclaringClass();
    //    }

    // Java中如果一个类型要实现动态代理，则必须实现InvocationHandler接口，实现invoke()方法
    // 查看Proxy源码
    // proxy.newProxyInstance();
    // 为特定的接口返回代理的实例对象，以此来分发(接口)中方法的调用给特定的"InvocationHandler"调用处理器
    // Returns a proxy instance for the specified interfaces that dispatches method invocations to the specified invocation handler.
    // public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
    //        Objects.requireNonNull(h);
    //        final Class<?> caller = System.getSecurityManager() == null ? null: Reflection.getCallerClass();
    //        Constructor<?> cons = getProxyConstructor(caller, loader, interfaces);
    //        return newProxyInstance(caller, cons, h);
    //    }
}
