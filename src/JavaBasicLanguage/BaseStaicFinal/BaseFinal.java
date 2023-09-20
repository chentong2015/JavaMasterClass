package JavaBasicLanguage.BaseStaicFinal;

// 使用final关键字修饰的class不能被继承
public final class BaseFinal {

    // 1. static final: 等效于const常量关键字
    // - 使用大写名称来命名, 值不能被修改
    // - 必须在声明的时候初始化, 或者在静态构造器中初始化
    // - Java为变量生成ConstantValue属性, 在准备阶段变量的值就会被初始为指定的初始值, 运算效率高
    public static final String CONST_VALUE = "Const value";

    // 2. Final值不能再被修改的Field，只能在声明时初始化或在构造器中初始化
    // Final修饰的Field不能添加setter方法
    public final int finalValue;

    // 所有的类型对象都只存储一个不变的值
    private static int classCounter = 0;

    // 3. 使用构造器初始化final的属性成员，可以对初始数据进行验证
    public BaseFinal() {
        classCounter++;
        this.finalValue = classCounter;
    }

    // 4. 使用final标记的方法不能在继承类中被重写，避免重写代码的算法和安全问题(密码存储)
    public final void testFinalMethod() {
        System.out.println("Cannot be override !");
    }

    // TODO. 方法内部的局部变量如果被设置成final, 则在第一次初始化之后不能再变动, 否则出现编译异常
    // Caused by: org.apache.maven.plugin.compiler.CompilationFailureException: Compilation failure
    // component/legacy/xxx/util/SqlHelper.java:[19,16]
    // error: cannot assign a value to final variable columnName
    private void testFinalVariable(int value) {
        final String columnName = "table_name";
        if (value > 10) {
            // columnName = "new name";
        }
    }
}
