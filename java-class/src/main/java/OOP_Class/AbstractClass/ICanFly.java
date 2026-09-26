package OOP_Class.AbstractClass;

// Fly 更多的是表示一种能力
public interface ICanFly {

    // 抽象出来的作用：
    // 1. 针对具体类型的鸟类具有不同的实现
    // 2. 非鸟类，也有具有飞行的能力，只需实现该接口
    void fly();
}
