package JavaBasicTypes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

// TODO. 使用BigDecimal可以定义浮点数的精确计算
// 不要使用float和double来完成需要精确的计算
public class JavaDecimal {

    public static void main(String[] args) {
        float f = Float.parseFloat("11.36");
        System.out.println(f);

        double d = Double.parseDouble("12.34");
        double d2 = Double.parseDouble("12.34");
        System.out.println(d + d2);
        System.out.println((d + d2) / 2.3d);

        BigDecimal bd1 = BigDecimal.valueOf(d);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        System.out.println(bd1.add(bd2));

        BigDecimal divisor = BigDecimal.valueOf(2.3d);
        System.out.println(bd1.add(bd2).divide(divisor, 5, RoundingMode.HALF_UP));
    }

    public void testBigDecimal() {
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(1);
        System.out.println(bigDecimal2 == bigDecimal1); // false 对象引用不同
        System.out.println(bigDecimal2.equals(bigDecimal1)); // ture 不可变类型所包含的值相同
    }

    // TODO. 格式化输出精确的浮点型数据
    public void testDecimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double input = 3.14159265359;
        System.out.println(decimalFormat.format(input)); // 3.14
    }
}
