package JavaBasicLanguage.Base05OOP.BaseMemberAndInnerClass;

import java.util.ArrayList;

/**
 * 类型可约束:
 * 1. public 能作用在Top Level顶级类型上，也能作用在 member classes上, Not to local classes or anonymous classes !!!
 * 2. protected & private 只能作用在 member classes上 !!!
 * 3. static 只能作用在member classes上 !!! Not to top level or local or anonymous classes ===> C#区别: 用static来声明静态类型 !!!
 */
public class GearBox {

    private int maxGears;
    private int currentGear;
    private boolean isClutchIn;
    private ArrayList<Gear> gears;
    private static int testValueStatic;

    public GearBox(int maxGears) {
        this.maxGears = maxGears;
        this.currentGear = 0;
        this.gears.add(0, new Gear(1, 1.0));
    }

    public void operateClutch(boolean in) {
        this.isClutchIn = in;
    }

    public void addGear(int number, double ratio) {
        if (number > 0 && number < gears.size()) {
            // Add instance of inner class !!
            gears.add(new Gear(number, ratio));
        }
    }

    public void changeGear(int newGear) {
        if (newGear > 0 && newGear < gears.size() && isClutchIn) {
            this.currentGear = newGear;
        } else {
            this.currentGear = 0;
        }
    }

    public double wheelSpeed(int revs) {
        if (isClutchIn) {
            System.out.println("Scream !!");
            return 0.0;
        }
        return gears.get(currentGear).getRatio();
    }

    // Member Class 非静态的嵌套类型  --------------------------------------------------------------------------------------
    // 0. <<使用嵌套类型包装, 实现一个"紧密关联"的内部逻辑 ==> 等价于将逻辑置于外部的类型>>
    // 1. Within the Context 在GearBox之外谈论Gear没有意义 !! 在上下文的约束环境中使用嵌套类型
    // 2. Gear作为内部的成员，通常需要在外部类型中创建出实例对象来使用
    // 3. 通常使用private限制外部的直接访问，通过外部类来实现逻辑
    /*
       GearBox gearBox = new GearBox(6);
       GearBox.Gear first = gearBox.new Gear(1, 2.0);  必须通过外部类型和它的实例 ==> 来创建内部类型的实例 !!!
       double currentSpeed = first.driveSpeed(100);  操作内部类型的对象
     */
    public class Gear {
        // 同名的变量，隐藏了外部类型的相同变量 !!
        private int gearNumber;
        private double ratio;

        public Gear(int gearNumber, double ratio) {
            this.gearNumber = gearNumber;
            this.ratio = ratio;
        }

        // 在"非静态嵌套类型"中 方法可以访问到外部类中的所有成员 !!
        // ===>  区别C#: 不提供这种默认的访问方式，需要将外部类型的对象传递到内部 !!
        public double getRatio() {
            System.out.println("Get outer non static value :" + currentGear);
            System.out.println("Get outer static value :" + testValueStatic);
            return ratio;
        }
    }

    // Static Member Class 静态的嵌套类型: associate a class with its outer class 行为和外部类型一致 -------------------------
    // 打包的时候，是挂载在外部的类型之下
    /*
        GearBox gearBox = new GearBox(10);
        GearBox.GearFil gearFil = new GearBox.GearFil(10); 完全是当作静态成员使用
        int gearFilNumber = gearFil.getGearFilNumber();
     */
    public static class GearFil {
        private int gearFilNumber;

        public GearFil(int gearFilNumber) {
            this.gearFilNumber = gearFilNumber;
        }

        // 在"静态嵌套类型"中可以访问到外部类型的所有静态成员 !!! 不能访问非静态的成员
        public int getGearFilNumber() {
            // 除非创建外部类型的实例
            // GearBox gearBox = new GearBox(10);
            // int index = gearBox.currentGear;

            System.out.println("Get outer static value :" + testValueStatic);
            return gearFilNumber;
        }
    }

    // 在Android开发中，对于View的Adapter适配器，一般会在内部声明一个静态的嵌套类型 !!!
    // RecyclerView.Adapter<RecyclerViewReservationAdapter.ViewHolder>
    // static class ViewHolder extends RecyclerView.ViewHolder {}
}
