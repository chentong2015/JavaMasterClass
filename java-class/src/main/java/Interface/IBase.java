package Interface;

public interface IBase {

    // TODO. 接口中每个Field成员都默认常量值, 不支持private属性
    public static final int id = 1;
    String CONFIG_USER = "master java";

    // 不能够含有构造器, 不能包含实例对象的行为
    // public IBase() {};

    // 接口中不能使用静态语句块 static { }
    // 编译器会为接口生成"<clinit>()"方法, 完成变量初始化的赋值
    // 执行接口的<clinit>()方法时，不需要先执行父接口的<clinit>()方法

    // TODO. 接口中可以申明嵌套的接, 默认标记static
    static interface ITest {
        void runTest();
    }
}
