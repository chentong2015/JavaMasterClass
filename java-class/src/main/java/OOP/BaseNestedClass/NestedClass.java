package OOP.BaseNestedClass;

public class NestedClass {

    private int currentGear;
    private static int testValueStatic;

    // TODO. 在外部类可以访问到嵌套类的私有成员(static或者非static)
    public void testGetPrivateFieldsOfNestedClass() {
        Gear gear = new Gear(10, 10.0);
        int number = gear.gearNumber;
    }

    // TODO: 非静态的成员(嵌套)类型 => 依赖外部类的实列化
    // Gear作为内部的成员，通常需要在外部类型中创建出实例对象来使用
    // 非静态成员类每个实例都包含一个额外的指向外部对象的引用，会导致外部对象GC时仍然保留 !!
    // 通常使用private限制外部的直接访问，通过外部类来实现逻辑
    //
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
}