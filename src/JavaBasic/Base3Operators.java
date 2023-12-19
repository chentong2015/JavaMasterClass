package JavaBasic;

// 1. Summary Operators Java 操作符全集
// 2. Java Operator Precedence Table 操作符的优先级
// 3. Associativity: Left to right, Right to left 运算的方向
public class Base3Operators {
    
    /**
     * 操作数，操作符 ==> 表达式
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
    public static void testOperators() {
        // 不要写始终为true的判断条件 !!!
        int value = 50;
        if (value == 50) {
            // Code block ==> A block fo code 语句块
            System.out.println("check the value");
        }

        // 注意以下的赋值方式的问题 !!!
        boolean isCheck = false;
        if (isCheck = true) {
            System.out.println("This is not supposed to happen");
        }
        // 正确的使用方式
        if (isCheck) {
            System.out.println("Should do like this way");
        }
        if (!isCheck) {
            System.out.println("Should check false condition");
        }

        // Ternary Operator
        int numCheck = isCheck ? 10 : 100;
    }
}
