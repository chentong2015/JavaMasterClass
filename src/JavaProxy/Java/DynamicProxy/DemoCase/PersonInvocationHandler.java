package JavaProxy.Java.DynamicProxy.DemoCase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PersonInvocationHandler<T> implements InvocationHandler {

    // 被代理对象引用，invoke方法里面method需要使用这个被代理对象
    private T target;

    public PersonInvocationHandler(T target) {
        this.target = target;
    }

    // 被动态代理类回调执行
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类 proxyClass =" + proxy.getClass());
        System.out.println("方法名: " + method.getName());
        System.out.println("方法返回类型：" + method.getReturnType());
        System.out.println("接口方法入参数组: " + (args == null ? "null" : Arrays.toString(args)));
        TimeMonitorUtil.start();
        Thread.sleep(10);
        // 调用呗代理对象的真实方法
        Object result = method.invoke(target, args);
        TimeMonitorUtil.finish(method.getName());
        return result;
    }
}
