package JavaBasicLanguage.Base01OOP.Inheritance;

public class Animal {

    private int size;
    private String name;

    // 默认的无参构造器 在继承链上被调用
    public Animal() {
        this(100, "test");
    }

    public Animal(int size, String name) {
        // this : to call current class members
        this.size = size;
        this.name = name;
    }

    // TODO: 受保护的构造器只能在同一个包内或继承链上被调用 
    // protected Animal() { }

    // 受保护的方法成员，只能在当前的package和它的子类中被访问到 (可在子类中进行重写)
    protected void testProtectedMethod() {
        System.out.println("Test Protected method");
    }

    // TODO: Java中默认实例方法都是虚方法，可以被继承类型重写的，除非使用final关键字
    public void eat() {
        System.out.println("Animal eat() called");
    }

    // 母类中声明的静态方法，属于这个类型
    // 可以使用类型名来调用静态方法
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
}
