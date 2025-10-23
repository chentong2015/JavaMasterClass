package JavaBasicTypes;

// TODO. JLS语言定义文档要求，为常用的指定范围的整数设置缓存
// - 装箱时全部使用同一个缓存对象的引用
// - 该cache数组只加载一次，根据index返回指定装箱的对象的引用
// - 为Integer设置"IntegerCache缓存"以支持在默认区间(-128,127]范围int值的自动装箱
//     public static Integer valueOf(int i) {
//        if (i >= IntegerCache.low && i <= IntegerCache.high)
//            return IntegerCache.cache[i + (-IntegerCache.low)];
//        return new Integer(i);
//     }
public class JavaIntegerCache {

    // TODO. 自动装箱(AutoBoxing)和拆箱(unBoxing)
    // 1. 将int值赋值给Integer类型的变量时，使用Integer.valueOf(i)进行自动装箱
    // 2. 将Integer变量赋值给int类型的变量时，自动拆箱
    // 3. 当Integer变量进行算术运算时，自动拆箱
    public static void main(String[] args) {
        Integer x = 100;
        Integer y = x;
        y = 200; // new Integer(200) 自动装箱构建新的对象，返回引用给变量

        Integer aa = 1;
        Integer bb = 1;
        System.out.println(aa == bb); // true aa和bb变量都会引用缓存中同一个装箱对象

        Integer aaa = 200;            // new Integer(200) 在范围之外，直接构建堆上新的对象
        Integer bbb = 200;
        System.out.println(aaa == bbb);      // false  运算比较引用而不是值
        System.out.println(aaa == bbb + 0);  // true   算术运算符会自动拆箱，转成int值比较
        System.out.println(aaa.equals(bbb)); // true   判断引用类型然后比较引用类型的字面值

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Long g = 3L;
        System.out.println(c == a + b);      // Ture   算术运算符, 自动拆箱比较值
        System.out.println(g == a + b);      // Ture   运算后自动拆箱, int和long类型比较时自动隐式转换
        System.out.println(c.equals(a + b)); // True   运算后自动装箱, 比较Integer类型对象的值
        System.out.println(g.equals(a + b)); // False  运算后自动装箱, equals()判断数据引用类型不匹配
    }
}
