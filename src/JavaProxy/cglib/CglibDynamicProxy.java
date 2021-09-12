package JavaProxy.cglib;

// TODO: CGLIB可以代理未实现任何接口的类，通过生成一个被代理类的子类来拦截被代理类的方法调用
// CGLIB(Code Generation Library)是一个基于ASM的字节码生成库
// 1. 允许在运行时对字节码进行修改和动态生成
// 2. 通过继承方式实现代理
// https://bbs.huaweicloud.com/blogs/215606

// Spring AOP模块:
// 如果目标对象实现了接口，则默认采用 JDK 动态代理，否则采用 CGLIB 动态代理
public class CglibDynamicProxy {

}
