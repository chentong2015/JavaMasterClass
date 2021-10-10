package JavaProxy.DynamicProxy;

import JavaProxy.DynamicProxy.model.Person;
import JavaProxy.DynamicProxy.model.PersonImpl;
import JavaProxy.DynamicProxy.model.PersonInvocationHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

// TODO: "动态"是指在程序运行时，基于反射动态的创建出动态代理类，而不是在运行期间确定的
//       JDK动态代理的特点是代理类必须继承Proxy类
//       JDK动态代理只能代理实现了接口的类 !!
// https://docs.oracle.com/javase/7/docs/technotes/guides/reflection/proxy.html
public class JdkDynamicProxyTest {

    // TODO: 动态代理是在运行时动态生成类字节码，并加载到JVM
    //       生成一个动态代理类，该类继承自Proxy类，同时实现Person接口(可调所有的方法)
    // $Proxy0是代理类在系统内部的编号
    // SimpleName = $Proxy0 name =com.sun.proxy.$Proxy0
    // implements Interfaces = [interface JavaProxy.DynamicProxy.model.Person]
    // superClass = class java.lang.reflect.Proxy

    // 为特定的接口返回代理的实例对象，以此来分发接口方法的调用给特定的"InvocationHandler"调用处理器
    // Returns a proxy instance for the specified interfaces that dispatches method invocations to the specified invocation handler.
    // public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
    //     Objects.requireNonNull(h);
    //     final Class<?> caller = System.getSecurityManager() == null ? null: Reflection.getCallerClass();
    //     Constructor<?> cons = getProxyConstructor(caller, loader, interfaces);
    //     return newProxyInstance(caller, cons, h);
    // }
    public static void testJavaDynamicProxy1() {
        // 创建一个实例对象, 这个对象是被代理的对象, 委托类
        Person person = new PersonImpl("chen");
        // 创建一个与代理类相关联的InvocationHandler, 每一个代理类都有一个关联的InvocationHandler, 并将代理类引用传递进去
        InvocationHandler Handler = new PersonInvocationHandler<>(person);
        // 创建一个代理对象personProxy来代理person
        // TODO: 创建的代理对象每个执行方法都会被替换执行InvocationHandler接口中invoke方法
        Person proxyInstance = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, Handler);
        showProxyClassInfos(proxyInstance.getClass());
        // 通过"代理类"执行"委托类"的代码逻辑, 最终会执行handler中invoke()方法
        String name = proxyInstance.getName();
        proxyInstance.work(name, "Place 01");
    }

    // TODO: Proxy.newProxyInstance()方法底层的细节
    // 根据自定义的InvocationHandler的实现，拿到对应的构造器来创建指定的动态代理类(对象)
    // loader – the class loader to define the proxy class
    // interfaces – the list of interfaces for the proxy class to implement
    // Returns: a proxy class that is defined in the specified class loader and that implements the specified interfaces
    //
    // public static Class<?> getProxyClass(ClassLoader loader,  Class<?>... interfaces)
    //     throws IllegalArgumentException {
    //     Class<?> caller = System.getSecurityManager() == null ? null : Reflection.getCallerClass();
    //     return getProxyConstructor(caller, loader, interfaces).getDeclaringClass();
    // }
    public static void testJavaDynamicProxy2() throws Exception {
        Person person = new PersonImpl("tong");
        InvocationHandler handler = new PersonInvocationHandler<>(person);

        // getProxyClass静态方法生成一个动态代理类，该类继承自Proxy类，实现Person接口
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), Person.class);
        showProxyClassInfos(proxyClass);
        // 拿到动态代理类指定参数(带有InvocationHandler参数)的构造器
        Constructor<?> ProxyConstructor = proxyClass.getConstructor(InvocationHandler.class);
        // 通过构造器创建一个动态代理类实例
        Person proxyInstance = (Person) ProxyConstructor.newInstance(handler);

        // 通过Proxy的静态方法来判断一个类型是否是动态代理类
        System.out.println("stuProxy isProxy " + Proxy.isProxyClass(proxyInstance.getClass()));
        // 获取并检测于动态代理类关联的InvocationHandler
        InvocationHandler handlerObject = Proxy.getInvocationHandler(proxyInstance);
        System.out.println(handlerObject.getClass().getName());

        String name = proxyInstance.getName();
        proxyInstance.work(name, "Place 02");
    }

    public static void saveGeneratedJdkProxyFiles() throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    private static void showProxyClassInfos(Class proxyClass) {
        System.out.println("package = " + proxyClass.getPackage()
                + " SimpleName = " + proxyClass.getSimpleName()
                + " name =" + proxyClass.getName()
                + " CanonicalName = " + "" + proxyClass.getCanonicalName()
                + " implements Interfaces = " + Arrays.toString(proxyClass.getInterfaces())
                + " superClass = " + proxyClass.getSuperclass()
                + " methods =" + Arrays.toString(proxyClass.getMethods()));
    }

    // 保存生成的代理类class
    // className  生成的代理类名称
    // interfaces 代理类需要实现的接口
    // pathDir    代理类保存的目录路径,以目录分隔符结尾
    public static void saveClass(String className, Class<?>[] interfaces, String pathDir) {
        // byte[] classFile = ProxyGenerator.generateProxyClass(className, interfaces);
        Path path1 = Paths.get(pathDir);
        if (!path1.toFile().exists()) {
            path1.toFile().mkdirs();
        }
        String path = pathDir + className + ".class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            // fos.write(classFile);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
