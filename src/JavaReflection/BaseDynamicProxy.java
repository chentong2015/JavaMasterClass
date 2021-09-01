package JavaReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// TODO: 动态代理的实现原理以及AOP的底层
// https://cloud.tencent.com/developer/article/1461796

// Java源码提供的动态代理类型 Dynamic Proxy Classes，基于Java内部的反射来实现
// java.lang.reflect.Proxy
// https://docs.oracle.com/javase/7/docs/technotes/guides/reflection/proxy.html
//
// https://www.huaweicloud.com/articles/696efe7865fb093d4217487b29fc2506.html
// https://bbs.huaweicloud.com/blogs/detail/191522
// https://bbs.huaweicloud.com/blogs/215606
//
// Java中如果一个类型要实现动态代理，则必须实现InvocationHandler接口，实现invoke()方法

// TODO: Mybatis 使用动态代理
// MapperRegister > MapperProxyFactory > Proxy.java > MapperProxy !! > MapperMethod
// MapperProxy 实现了InvocationHandler接口
public class BaseDynamicProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    // 通过字节码文件反射 ?
    //  @Deprecated
    //    @CallerSensitive
    //    public static Class<?> getProxyClass(ClassLoader loader,
    //                                         Class<?>... interfaces)
    //        throws IllegalArgumentException
    //    {
    //        Class<?> caller = System.getSecurityManager() == null
    //                              ? null
    //                              : Reflection.getCallerClass();
    //
    //        return getProxyConstructor(caller, loader, interfaces)
    //            .getDeclaringClass();
    //    }

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
