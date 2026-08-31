package OOP.BaseNestedClass;

import java.util.function.Function;

public abstract class NestedStaticClass {

    protected String outsideName;
    private static int testValueStatic;

    // 静态的方法中不能调用非静态的成员
    private static String getName() {
        // return outsideName;
        return "null";
    }

    // TODO. 静态成员类是外围类的一个静态成员，和其他静态成员一样，不是"inner class"
    static class InnerClass {
        public void testOutsideName() {
            // 不能直接调用外部的非静态属性
            // outsideName = "";

            // 可以调用外部静态的方法
            getName();
        }
    }

    // 抽象类中的公开静态类，和非抽象类中的公开静态类创建对象方式一致
    // StaticMemberClass.InnerPublicClass innerClass = new StaticMemberClass.InnerPublicClass();
    public static class InnerPublicClass<R> {

        private Function<String, R> function;
    }

    // TODO: 静态的成员(嵌套)类型: Static Member Class => 不依赖于外部类的实化
    //  可以当作是类型的静态成员来使用
    //  行为和外部类型一致，打包的时候，将会挂载在外部类型之下
    //
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
