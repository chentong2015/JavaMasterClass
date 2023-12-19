package JavaBasicLanguage.BaseInterface.base;

// 接口中的每一个Field成员都是(隐式)常量值:
// public static final (constant value)
public interface ITelephone {

    // 1. 接口中可声明Fields, 通过接口名称调用ITelephone.Field_Name
    public static final int id = 1;
    String CONFIG_USER = "master java";

    // 2. 不能够含有构造器
    // protected String getPhoneValue();

    // 3. 只提供方法的签名
    // TODO. 默认使用public修饰且只能使用public
    public void powerOn();

    // 4. 接口中可以申明嵌套的接口
    // interface ISourceReader {
    // 	  void onStartup(int value);
    // }

    // 5. 嵌套的接口不需要标记static
    static interface ITest {
        void runTest();
    }

    // 6. 接口中的方法不能设置成final
    // final long getId();
}
