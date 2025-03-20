package JavaCoreOOP.BaseNestedClass;

// Nested 嵌套 / Member Class 成员类
// 0. 使用嵌套类型包装实现"紧密关联"的内部逻辑，等价于将逻辑置于外部的类型
// 1. 在上下文的约束环境(Within the Context)中使用嵌套类型
// 在外部内之外谈论内部类没有意义，成员类在.java文件列表上不会显式
public class BaseNestedClass {

    private int currentGear;
    private static int testValueStatic;

    // TODO. 在外部类可以访问到嵌套类的私有成员(static或者非static)
    public void testGetPrivateFieldsOfNestedClass() {
        Gear gear = new Gear(10, 10.0);
        int number = gear.gearNumber;
    }

    // 成员类型可以使用四种修饰符: public protected private static

    // TODO: Gear作为内部的成员，通常需要在外部类型中创建出实例对象来使用, 需要依赖外部类的实列化
    //    非静态成员类每个实例都包含一个额外的指向外部对象的引用，会导致外部对象GC时仍然保留 !!
    // 1. 非静态的成员(嵌套)类型
    // 通常使用private限制外部的直接访问，通过外部类来实现逻辑
    // GearBox gearBox = new GearBox(6);
    // GearBox.Gear first = gearBox.new Gear(1, 2.0);
    // double currentSpeed = first.driveSpeed(100);
    public class Gear {
        private int gearNumber;
        private double ratio;

        public Gear(int gearNumber, double ratio) {
            this.gearNumber = gearNumber;
            this.ratio = ratio;
        }

        // 在"非静态嵌套类型"中 方法可以访问到外部类中的所有成员
        public double getRatio() {
            System.out.println("Outer non static value :" + currentGear);
            System.out.println("Outer static value :" + testValueStatic);
            return ratio;
        }
    }

    // TODO: 静态嵌套类不依赖于外部类的实例化，可以当作是类型的静态成员来使用
    // 2. 静态的成员(嵌套)类型: Static Member Class
    // Associate a class with its outer class 行为和外部类型一致，打包的时候，将会挂载在外部类型之下
    // GearBox.GearFil gearFil = new GearBox.GearFil(10);
    // int gearFilNumber = gearFil.getGearFilNumber();
    public static class GearFil {
        private int gearFilNumber;

        public GearFil(int gearFilNumber) {
            this.gearFilNumber = gearFilNumber;
        }

        // 1. "静态嵌套类"中可以访问到外部类型的所有静态成员
        // 2. "静态嵌套类"不能访问外部类型的非静态成员，需要创建外部类型的对象
        public int getGearFilNumber() {
            System.out.println("Outer static value :" + testValueStatic);
            return gearFilNumber;
        }
    }
}