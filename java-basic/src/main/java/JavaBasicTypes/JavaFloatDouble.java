package JavaBasicTypes;

// TODO. float和double浮点型都是非精确的数据
// - Float 32 bits单精度浮点类型
// - Double 64 bits双精度
public class JavaFloatDouble {

    // TODO: 如果不添加f或d的后缀
    //  java默认处理整型数据成int类型
    //  java默认处理浮点数据成double类型
    public static void main(String[] args) {
        float myMinFloatValue = Float.MIN_VALUE;
        float myFloatTestValue = 5.25f; // 纯5.25的字面值会被视为是double

        float myFloatValue = 5f;  // 字面值5视为int, 自动转成float没有问题
        System.out.println("float value =" + myFloatValue);      // 5.0
        System.out.println("float value =" + myFloatValue / 2f); // 2.5
        System.out.println("float value =" + myFloatValue / 3f); // 1.6666666


        double myMinDoubleValue = Double.MIN_VALUE;
        double myDoubleTestValue = 5.25d; // 5.25类型默认成立， 但是推荐使用d修饰
        double doubleNumber = 3_000_000.4_567_890d; // 同样支持下划线的写法 = 3000000.4567890

        double myDoubleValue = 5d;
        System.out.println("double value =" + myDoubleValue);      // 5.0
        System.out.println("double value =" + myDoubleValue / 2d); // 2.5
        System.out.println("double value =" + myDoubleValue / 3d); // 1.666666666...7
        System.out.println("double value =" + 5.00d / 3.00d);      // 1.666666666...7

        // 格式化输出double类型的数据值
        double pi = 3.1415926d;
        String valueFormatted = String.format("%.2f", pi);
        System.out.println(valueFormatted);

        // TODO. 使用格式化来Round小数点后的数据
        String average = String.format("%.5f", myDoubleValue / 3);
        double result = Double.parseDouble(average);
    }
}
