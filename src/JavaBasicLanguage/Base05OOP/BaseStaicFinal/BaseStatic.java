package JavaBasicLanguage.Base05OOP.BaseStaicFinal;

// MonoState ==> 一种软件开发模式，实现单列的类似模式
public class BaseStatic {

    // 属于类型的实例对象 ==> 每个类型对象可能拥有不同的状态
    private String objectStr;

    // 所有的实例共享相同的静态成员 !!
    protected static int id;
    private static String staticStr;

    // Const常量
    private static final String CONST_VALUE;

    // 静态构造器:
    // 1. executed only once when the class is first loaded into the project !!!
    // 2. 在Class被初始化的时候，有编译器自动调用执行，并且优先于实例构造器
    // 3. 不能使用this, super, 任何的type variables !!              ====> C#区别：static ClassName() { ... }
    // 4. 可以在静态构造器中初始化常量 !!
    // 5. 不能在构造器上面直接throws Exception !! There is no way of our code to anywhere to catch any exceptions that are thrown !
    static {
        staticStr = "init static value";
        CONST_VALUE = "init const value";
        System.out.println("First static constructor");
    }

    // 多个静态构造器的调用顺序与它们的而声明顺序一致 !!!!                ====> C#区别：C#不允许声明多个静态构造器 !!!
    static {
        System.out.println("Second static constructor");
    }

    // 实例构造器：可调用静态或者非静态成员
    public BaseStatic() {
        objectStr = "init object value";
        BaseStatic.id = 100;
        staticStr = "init Instance value";
        System.out.println(staticStr);
    }

    // 注意：实例方法中可以访问类型的静态或者非静态的成员: 属性和方法  !!!!
    //      包括从母类继承下来的静态的成员 !!!!
    public void testStaticMethod() {
        id = 10;
        testStatic();
    }

    // 1. 只能访问类型的静态成员: 属性和方法  !!!!
    // 2. 不能够访问到this
    // 3. 如果一个方法没有使用到实例的成员(变量) 那么应该声明成静态的成员方法 !!!! ===> 因为不需要创建实例对象就能使用方法(的逻辑)
    public static void testStatic() {
        staticStr = "OtherTech";
        System.out.println("Test Static");
    }
}
