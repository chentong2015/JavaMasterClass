package JavaBasicOOP.StaicFinal;

// MonoState 软件开发模式，实现单列的类似模式
public class BaseStatic {

    // 属于类型的实例对象, 每个类型对象可能拥有不同的状态
    private final String instanceStr;

    // 所有的实例共享相同的静态成员
    protected static int id;
    private static String staticStr;

    // Const常量: 在Linking>Preparation准备阶段便会被初始化成所指定的初始值
    private static final String CONST_VALUE = "init const value";

    // TODO: 静态构造器的声明
    // 0. 只会被调用一次，保证在子类的<clinit>()方法执行前，父类的<clinit>()方法已经执行完毕
    // 1. 在Class被"初始化"时由编译器自动调用执行，并且优先于实例构造器
    // 2. 可以在静态构造器中初始化常量
    // 3. 不能使用this, super, 任何的type variables
    // 4. 不能在构造器上面直接throws Exception, 由JVM负责调用，无法捕获异常
    static {
        staticStr = "init static value";
        System.out.println("First static constructor");
    }

    // 申明多个静态构造器: 静态构造器的调用顺序与它们的而声明顺序一致
    static {
        System.out.println("Second static constructor");
    }

    // 实例构造器：可调用静态或者非静态成员
    public BaseStatic() {
        instanceStr = "init object value";
        BaseStatic.id = 100;
        staticStr = "init Instance value";
        System.out.println(staticStr);
    }

    // - 实例方法中可以访问类型的静态或者非静态的成员: 属性和方法 + 母类继承的静态的成员
    // - 如果方法需要构建实例对象才能使用，则声明成实例方法
    public void testInstanceMethod() {
        // TODO：不能访问静态的属性成员，对于属性成员的修改会对其它的对象造成影响
        id = 10;
        staticStr = "static";
        testStaticMethod();
    }

    // - 静态方法中不能访问到this, 只能访问到类型的静态成员(static属性或static方法)
    // - 如果一个方法没有使用实例的成员(变量)且不需通过对象来调用，则应声明成静态的成员方法
    public static void testStaticMethod() {
        staticStr = "OtherTech";
        System.out.println("Test Static");
    }
}
