package core_types.integer;

// TODO. 注意不同类型值的转换
// short短整型能表示的字节长度2 bytes
// long长整型能表示的字节长度8 bytes
public class JavaShortLong {

    public static void main(String[] args) {
        short myShortMinValue = Short.MIN_VALUE;
        int result = myShortMinValue / 2;               // 隐含的类型转换int
        short newValue = (short) (myShortMinValue / 2); // 显示的强制类型转换

        // 后面的字面值会被视为是int，然后检测是否满足要转换成的类型值的范围
        short bigShortLiteralValue = 32767;

        // TODO. 推荐显示标记L类型
        // 不写L 会被自动的处理成int，然后隐式转long
        long myLongValue = 100L;
        long myLongMinValue = Long.MIN_VALUE;
        long myLongMaxValue = Long.MAX_VALUE;
    }
}