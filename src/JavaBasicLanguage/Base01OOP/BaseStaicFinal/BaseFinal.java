package JavaBasicLanguage.Base01OOP.BaseStaicFinal;

// 使用final关键字修饰的class不能被继承 !!!     ====> C#使用sealed来声明不能被继承的类型 !!
public final class BaseFinal {

    // 1. Constant：
    //    必须在声明的时候初始化, 或者在静态构造器中初始化 !!
    //    使用大写名称来命名, 值不能被修改, 运算效率高             ====>  C#区别：直接使用const关键字来声明 !!
    //    const关键字没有再使用 ===> 等效于 static final
    public static final String CONST_VALUE = "Const value";

    // 2. Final 值不能再被修改的Field：只能在声明的时候初始化，或者在构造器中初始化 !!
    //    Final 修饰的Field不能添加getter方法 !!!
    public final int finalValue;

    // 所有的类型对象都只存储一个不变的值
    private static int classCounter = 0;

    // 3. 使用构造器初始化final的属性成员，可以对初始数据进行验证
    public BaseFinal() {
        classCounter++;
        this.finalValue = classCounter;
    }

    // 4. 使用final标记的方法不能被重写 (在继承类中)  ===> 避免重新代码的算法和安全问题(密码存储) !!
    public final void testFinalMethod() {
        System.out.println("Cannot be override !");
    }
}
