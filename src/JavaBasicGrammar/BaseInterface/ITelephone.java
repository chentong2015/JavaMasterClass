package JavaBasicGrammar.BaseInterface;

// 接口的认识: Abstract level 抽象和解耦的关键
// 1. Abstract, only supply the actual method names and the parameters
//    本身是抽象的，所有和实例(化)相关的都不能做
// 2. Provide a common behavior, and be used by several classes
//    提供一种公有的行为(方法); 可以被多个不相关的类型实现
// 3. A commitment, a contract, the signature of method will not change
//    提供一种协议，保证方法的签名不会改变，同时具有多种实现的方式
// 4. 提供一种分离行为的能力: 接口隔离

// 接口中的每一个Field成员都是(隐式)常量值:
// public static final (constant value)
public interface ITelephone {

    // 1. 接口中可以声明Fields
    public static final int id = 1;

    // 2. 不能够含有构造器
    // protected String getPhoneValue();

    // 3. 只提供方法的签名(默认是public修饰的)
    public void powerOn();

    void dial(int phoneNumber);

    // 4. 接口中可以申明嵌套的接口
    // interface IInnerInterface {
    // 	  void onStartup(int value);
    // }
}
