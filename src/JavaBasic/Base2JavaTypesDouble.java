package JavaBasic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Base2JavaTypesDouble {

    // TODO. 不要使用float和double来完成需要精确答案的任务
    // float -> Float 单精度浮点类型 32 bits ==> 4 bytes
    // 浮点数在内存存储：表示成2进制 > 2进制科学计数法 > 填32位bit: 偏移 + 零舍1入
    private void testFloatDouble() {
        float myMinFloatValue = Float.MIN_VALUE;
        float myFloatTestValue = 5.25f; // 纯5.25的字面值会被视为是double
        float myFloatValue = 5f; // 字面值5视为int, 自动转成float没有问题
        System.out.println("float value =" + myFloatValue); // 5.0
        System.out.println("float value =" + myFloatValue / 2f); // 2.5
        System.out.println("float value =" + myFloatValue / 3f); // 1.6666666
    }

    // TODO: 如果不添加f或d的后缀，java默认处理浮点类型成double类型
    // double -> Double 双精度浮点类型 64 bits ==> 8 bytes
    private void testJavaDouble() {
        double myMinDoubleValue = Double.MIN_VALUE;
        double myDoubleTestValue = 5.25d; // 5.25类型默认成立， 但是推荐使用d修饰
        double pi = 3.1415926d;
        double doubleNumber = 3_000_000.4_567_890d;        // 同样支持下划线的写法 = 3000000.4567890

        double myDoubleValue = 5d;
        System.out.println("double value =" + myDoubleValue);      // 5.0
        System.out.println("double value =" + myDoubleValue / 2d); // 2.5
        System.out.println("double value =" + myDoubleValue / 3d); // 1.666666666...7
        System.out.println("double value =" + 5.00d / 3.00d);      // 1.666666666...7

        // 格式化double的数值, 输出指定格式的字符串形式
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double input = 3.14159265359;
        System.out.println(decimalFormat.format(input)); // 3.14
        String valueFormatted = String.format("%.2f", pi);
    }

    // 11.36
    // 10.730434782608697
    // 10.73043 使用BigDecimal可以定义浮点数的精确计算
    public static void main(String[] args) {
        float f = Float.parseFloat("11.36");
        System.out.println(f);

        double d = Double.parseDouble("12.34");
        double d2 = Double.parseDouble("12.34");
        System.out.println((d + d2) / 2.3d);

        BigDecimal bd1 = BigDecimal.valueOf(d);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        BigDecimal divisor = BigDecimal.valueOf(2.3d);
        System.out.println(bd1.add(bd2).divide(divisor, 5, RoundingMode.HALF_UP));
    }
}
