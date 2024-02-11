package JavaBasic;

// TODO. Java操作符注意
// 1. Java Operator Precedence Table 操作符的优先级
// 2. Associativity: Left to right, Right to left 运算的方向

/**
 * Java Summary Operators  操作符全集
 *
 * Simple Assignment Operator
 * =       Simple assignment operator
 * Arithmetic Operators
 * +       Additive operator (also used for String concatenation 用在字符串的级联)
 * -       Subtraction operator
 * Multiplication operator
 * /       Division operator
 * %       Remainder operator
 * Unary Operators
 * +       Unary plus operator; indicates
 * positive value (numbers are positive without this, however)
 * -       Unary minus operator; negates an expression
 * ++      Increment operator; increments a value by 1
 * --      Decrement operator; decrements a value by 1
 * !       Logical complement operator; inverts the value of a boolean
 * Equality and Relational Operators
 * ==      Equal to
 * !=      Not equal to
 * >       Greater than 作用在primitive types类型上面，直接比较
 * >=      Greater than or equal to
 * <       Less than
 * <=      Less than or equal to Conditional Operators
 * &&      Conditional-AND
 * ||      Conditional-OR
 * ?:      Ternary (shorthand for if-then-else statement)
 * Type Comparison Operator
 * instanceof      Compares an object to a specified type
 * Bitwise and Bit Shift Operators
 * ~       Unary bitwise complement
 * <<      Signed left shift
 * >>      Signed right shift
 * >>>     Unsigned right shift
 * &       Bitwise AND
 * ^       Bitwise exclusive OR
 * |       Bitwise inclusive OR
 * ::      Method reference (> Java 8) 方法引用操作符, 将方法作为参数传递
 */
public class Base3JavaOperators {

    // 注意赋值和比较符号的使用
    public void testOperatorsAssignment() {
        boolean isCheck = false;
        if (isCheck = true) {
            System.out.println("This is not supposed to happen");
        }
        if (isCheck) {
            System.out.println("Should do like this way");
        }
        if (!isCheck) {
            System.out.println("Should check false condition");
        }
    }

    // Ternary Operator
    public void testTernaryOperator(boolean isCheck, int index, int count) {
        int numCheck = isCheck ? 10 : 100;

        // TODO. ++ 自增运行符没有返回值，不能用来赋值
        //       + 1 运算符表达式结果才能用于赋值
        int value1 = index == 10 ? 0: index++;
        int value2 = count == 10 ? 0: count + 1;

        // TODO. 三元运算符会根据两个表达式的类型来判断(可能由于拆箱造成NullPointerException)
        Integer a = null;
        Integer a1 = null;
        Integer b = isCheck ? 2 : a;
        Integer c = isCheck ? null : a1;
    }
}
