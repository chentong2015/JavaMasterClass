package JavaProxy.Java.DynamicProxy.DemoCase;

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

public class JdkDynamicProxyTest {

    // 如何使用Java JDK实现的动态代理
    public static void main(String[] args) throws Exception {
        testJavaDynamicProxy1();
        testJavaDynamicProxy2();
    }

    // TODO: 通过Proxy.newProxyInstance()方法获取代理对象
    public static void testJavaDynamicProxy1() throws Exception {
        saveGeneratedJdkProxyFiles();
        // 创建一个实例对象, 这个对象是被代理的对象, 委托类
        Person person = new PersonImpl("chen");
        // 创建一个与代理类相关联的InvocationHandler, 每一个代理类都有一个关联的InvocationHandler, 并将代理类引用传递进去
        InvocationHandler Handler = new PersonInvocationHandler<>(person);
        // 创建一个代理对象personProxy来代理person
        // 创建的代理对象每个执行方法都会被替换执行InvocationHandler接口中invoke方法
        Person personProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, Handler);
        /** 代理类信息 */
        System.out.println("package = " + personProxy.getClass().getPackage()
                + " SimpleName = " + personProxy.getClass().getSimpleName()
                + " name =" + personProxy.getClass().getName()
                + " CanonicalName = " + "" + personProxy.getClass().getCanonicalName()
                + " 实现的接口 Interfaces = " + Arrays.toString(personProxy.getClass().getInterfaces())
                + " superClass = " + personProxy.getClass().getSuperclass()
                + " methods =" + Arrays.toString(personProxy.getClass().getMethods()));
        // 通过"代理类"执行"委托类"的代码逻辑, 最终会执行handler中invoke()方法
        personProxy.work(personProxy.getName(), "Place 01");
    }

    /**
     * 动态代理对象步骤
     * 1、 创建一个与代理对象相关联的 InvocationHandler，以及真实的委托类实例
     * 2、Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass，该类继承Proxy类，实现 Person.java接口；
     * JDK动态代理的特点是代理类必须继承Proxy类
     * 3、通过代理类 proxyClass 获得他的带InvocationHandler 接口的构造函数 ProxyConstructor
     * 4、通过 构造函数实例 ProxyConstructor 实例化一个代理对象，并将  InvocationHandler 接口实例传递给代理类。
     */
    public static void testJavaDynamicProxy2() throws Exception {
        // 1、创建 InvocationHandler 实例并设置代理的目标类对象
        Person person = new PersonImpl("tong");
        InvocationHandler Handler = new PersonInvocationHandler<>(person);
        // 2 创建代理类是一个字节码文件, 把proxyClass保存起来就能看到他继承Proxy类，实现Person接口
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});
        /** 代理类信息 */
        System.out.println("package = " + proxyClass.getPackage()
                + " SimpleName = " + proxyClass.getSimpleName()
                + " name =" + proxyClass.getName()
                + " CanonicalName = " + "" + proxyClass.getCanonicalName()
                + " 实现的接口 Interfaces = " + Arrays.toString(proxyClass.getInterfaces())
                + " superClass = " + proxyClass.getSuperclass()
                + " methods =" + Arrays.toString(proxyClass.getMethods()));
        // 3、通过proxyClass获得一个带有InvocationHandler参数的构造器constructor
        Constructor<?> ProxyConstructor = proxyClass.getConstructor(InvocationHandler.class);

        // 4、通过构造器创建一个动态代理类实例
        Person personProxy = (Person) ProxyConstructor.newInstance(Handler);
        System.out.println("stuProxy isProxy " + Proxy.isProxyClass(personProxy.getClass()));
        // 获取代理类关联的InvocationHandler
        InvocationHandler handlerObject = Proxy.getInvocationHandler(personProxy);
        System.out.println(handlerObject.getClass().getName());
        personProxy.work(personProxy.getName(), "Place 02");
        // saveClass("$PersonProxy0", proxyClass.getInterfaces(), "D:/123/");
    }

    public static void saveGeneratedJdkProxyFiles() throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
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
