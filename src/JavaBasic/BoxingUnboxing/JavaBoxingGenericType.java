package JavaBasic.BoxingUnboxing;

import java.util.ArrayList;
import java.util.List;

// TODO. AutoBoxing自动装箱和拆箱在泛型中的运用
// 1. 泛型参数必须使用包装器(包装类)，Java不支持原始类型(Primitive Type)的泛型
//    - Java原始类型不自动装箱就无法转型为Object，导致“类型擦除”之后没有办法插入强制类型转换
//    - 对于的包装器类型能够在泛型的实现中做强制类型转换使用
// 2. 泛型在运算过程中会频繁的进行装箱和拆箱，造成一定性能开销
public class JavaBoxingGenericType {

    // Java泛型中无数构造包装类和装箱，拆箱的开销，使得泛型比较慢 !!
    public static void testAutoBoxingAndUnBoxingList() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            // nums.add(Integer.valueOf(i));
            // AutoBoxing 自动的将值类型装箱成对应的引用类型
            nums.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            // nums.get(i).intValue();
            // Unboxing 自动的将引用类型拆箱成值类型
            System.out.println(nums.get(i));
        }
    }
}
