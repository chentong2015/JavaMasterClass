package OOP.BaseAbstractClass.model;

// Fly 更多的是表示一种能力 Can-Do something，或取名为CanFly
// IDEA具有明确的标识这是一个接口("I"在有些时候不是必须的写法)
public interface IFly {

    // 抽象出来的作用：
    // 1. 针对具体类型的鸟类具有不同的实现
    // 2. 非鸟类，也有具有飞行的能力，只需实现该接口
    void fly();
}
