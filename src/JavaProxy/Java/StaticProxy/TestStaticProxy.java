package JavaProxy.Java.StaticProxy;

public class TestStaticProxy {

    // 静态代理：
    // 1. 不需要通过反射，在"编译时"确定
    // 3. 客户端依赖公共接口并使用代理类，直接调用实际对象的方法
    public void testStaticProxy() {
        Bird tiger = new Bird();
        tiger.running(); // 直接调用
        Animal proxy = new AnimalProxy(tiger);
        proxy.running(); // 通过代理间接调用
    }
}
