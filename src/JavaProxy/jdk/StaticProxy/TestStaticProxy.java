package JavaProxy.jdk.StaticProxy;

import JavaProxy.jdk.StaticProxy.model.Animal;
import JavaProxy.jdk.StaticProxy.model.Bird;
import JavaProxy.jdk.StaticProxy.model.ProxyAnimal;

// 静态代理：
// 1. 不需要通过反射，在"编译时"确定
// 2. TODO: 必须为每个目标类型单独创建一个代理类
public class TestStaticProxy {

    public void testStaticProxy() {
        Bird bird = new Bird();
        bird.running(); // 直接调用
        Animal proxy = new ProxyAnimal(bird);
        proxy.running(); // 通过代理间接调用
    }
}
