package JavaProxy.StaticProxy;

import JavaProxy.StaticProxy.model.Animal;
import JavaProxy.StaticProxy.model.Bird;
import JavaProxy.StaticProxy.model.ProxyAnimal;

// 静态代理：在"编译时"确定, 无需反射, 必须为每个被代理对象创建一个代理类
public class TestStaticProxy {

    public void testStaticProxy() {
        Bird bird = new Bird();
        bird.running(); // 直接调用
        Animal proxy = new ProxyAnimal(bird);
        proxy.running(); // 通过代理间接调用
    }
}
