package JavaBasicLanguage.Base01OOP.OOP.Inheritance;

public class Animal {

    private int size;
    private String name;

    // 默认的无参构造器 在继承链上被调用
    public Animal() {
        this(100, "test");
    }

    // TODO: 受保护的构造器只能在同一个包内或继承链上被调用
    // protected Animal() { }

    public Animal(int size, String name) {
        // this : to call current class members
        this.size = size;
        this.name = name;
    }

    public void eat() {
        System.out.println("Animal eat() called");
    }

    // protected 能够被子类访问到，并且重写的方法
    protected void eatPlus() {
        System.out.println("This is a test");
    }

    // 母类中声明的静态方法，属于这个类型
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
}
