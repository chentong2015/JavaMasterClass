package JavaBasic;

// Statement: a complete unit of execution
// 1. Declaration Statement
// 2. Expression Statement: Assignment, Increment/Decrement, Method calls, Object Creation
// 3. Control-Flow Statement
public class Base5StatementCodeBlocks {

    public static void testStatement() {
        // 由(myVariable = 50 表达式)构成一个完整Expression Statement
        int myVariable = 50;
        System.out.println("This is a statement");
    }

    /**
     * Whitespace 空格
     * 1. 空格的作用是方便阅读，多余的空格会占据源代码文件的大小
     * 2. Java在编译源码成可执行程序的时候会忽略这些空格，空格不会对程序的执行速度和最中的运行结果造成影响
     */
    public static void testWhitespace() {
        // 可以使statement声明在多行: 在某些时候优化代码的阅读
        String demoWhitespaceString =
                "this is a test string";
        System.out.println("This is " +
                "another");
    }

    // indentation 代码的层级关系(缩进): adding space to program structure, 更加方便开发者阅读
    public static void testIndentation() {
        int salary = 20;
        if (salary > 20) {
            if (salary > 100) {
                System.out.println("Nested code block");
            }
        }
    }

    // Code Block: All codes within { } is a logical unit, 语句块可以嵌套
    public static void testCodeBlocks() {
        boolean gameOver = true;
        int score = 500;
        if (score == 400) {
            System.out.println("your score is 400");
        } else if (score == 500) {
            System.out.println("your score is 500");
        } else {
            System.out.println("game over");
        }
        if (gameOver) {
            // 这里的变量finalScore仅仅作用在这个if声明语句块的作用域内部
            // 之后会被自动清理Garbage Collection, 不能在作用域Scope之外访问
            int finalScore = score + 100;
        }
    }
}
