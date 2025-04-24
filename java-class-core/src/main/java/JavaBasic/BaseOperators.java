package JavaBasic;

public class BaseOperators {

    // TODO. 注意"前自增自减"和"后自增自减"符合的区别
    // 前自增自减: 先移动，后判断
    // 后自增自减: 先判断，后计算
    public static void main(String[] args) {
        int[] num = {0, 1};
        if (++num[0] > 0) {
            System.out.println("check 0");
        }
        // TODO. 在if条件判断中使用后自增: 先取值判断，后自增
        if (num[1]++ > 0) {
            System.out.println("check 1");
        }
        System.out.println(num[0]);
        System.out.println(num[1]);

        // TODO. 无论判断结果，一定会执行自增计算 !!
        int start = 0;
        if (num[start++]++ > 1) {
            System.out.println("check start");
        }
        System.out.println(num[0]);
        System.out.println(start);
    }

    // Ternary Operator 三元运算符号
    public void testTernaryOperator(boolean isCheck, int index, int count) {
        int numCheck = isCheck ? 10 : 100;

        // TODO. ++ 自增运行符没有返回值，不能用来赋值
        //       + 1 运算符表达式结果才能用于赋值
        int value1 = index == 10 ? 0: index++;
        int value2 = count == 10 ? 0: count + 1;

        // TODO. 三元运算符会根据两个表达式的类型来判断(可能拆箱造成NullPointerException)
        Integer a = null;
        Integer a1 = null;
        Integer b = isCheck ? 2 : a;
        Integer c = isCheck ? null : a1;
    }
}
