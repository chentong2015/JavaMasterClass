package JavaBasicLanguage.Base06PackagesScope;

public class BaseScopeVisibility {

    private int privateVar = 0;
    public int publicVar = 1;

    public BaseScopeVisibility() {
        System.out.println("private var = " + privateVar); // 直接找到类型的成员field
        System.out.println("public var = " + publicVar);
    }

    public int getPrivateVar() {
        return privateVar;
    }

    public void setPrivateVar(int privateVar) {
        this.privateVar = privateVar;
    }

    public void timesTwo() {
        int privateVar = 2; // 局部变量值作用在方法中
        for (int i = 0; i < 10; i++) { // i变量的作用域只在for block中
            System.out.println("use local var = " + privateVar);  // 如果当前的Scope没有，则找到最近的Scope的变量声明
            System.out.println("use outside var = " + this.privateVar); // 使用this使用(当前)类型的成员变量 !!!
        }
    }

    public void useInnerClass() {
        InnerClass innerClass = new InnerClass();
        System.out.println("Find inner value: " + innerClass.privateStr); // 内部类型的私有成员在外部类型中也是可见的 !! 通过实例对象 !!!
    }

    // 在Inner Class中可以直接使用Outer Class的Fields成员
    public class InnerClass {
        public int privateVar = 3;
        private String privateStr = "inner string"; // 该成员只对自身类型内部和它的外部(声明出来的对象)可见 !!!

        public InnerClass() {
            System.out.println("Inner class with " + privateVar);
        }

        public void timesTwo() {
            int privateVar = 4;
            for (int i = 0; i < 10; i++) {
                System.out.println("use local var = " + privateVar); // 4
                System.out.println("use outside var = " + this.privateVar); // 3
                System.out.println("Use outer class var = " + BaseScopeVisibility.this.privateVar); // 1 指定外部的类型 !!
            }
        }
    }
}
