package OOP_Types.Interface;

public interface IBase {

    // 不支持private属性
    public static final int id = 1;

    // TODO. 每个Field成员都默认常量值
    String CONFIG_USER = "master java";

    // 不能够含有构造器, 不能包含实例对象的行为
    // public IBase() {};

    // 不能声明静态构造器, 在类加载时自动初始化
    // static { }

    // TODO. 可以申明嵌套的接口, 默认标记static
    static interface ITest {
        void runTest();
    }
}
