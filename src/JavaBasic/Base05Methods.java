package JavaBasic;

public class Base05Methods {

    // Java 支持一组参数作为输出信息                      =======> C#数组参数: void Test(int value, params string[] array) {}
    // ... 参数只能作为参数的最后一个位置
    // 调用方法 testMoreParameters(1, "para1", "para2")
    private static void testMoreParameters(int value, String... more) {
        // 需要判断引用类型是否为空 !!!
        if (more != null) {
            for (String str : more) {
                System.out.println(str);
            }
        }
    }

    // 1. java不支持设置方法参数的默认值                   =======> C#区别: 支持直接设置参数的默认值，同时支持ref & out关键字修饰
    // 2. 方法的参数控制在3个之内, 一个抽象层级 !!!
    public static int testMethods(boolean gameOver, int score, int levelCompleted, int bonus) {
        // 内部会自动的创建局部变量, 在方法完成，返回的时候Garbage Collection
        int position = 4; // 可以使用一个变量来作为返回的值 !!
        if (score >= 100) {
            return 1;
        } else if (score >= 50) { // 注意: 这一行的判断条件可以优化，排除到前面if出现的条件
            return 2;
        } else if (score >= 10) {
            return 3;
        } // 可以不用else
        return -1; // -1 通常是表示错误，或者是没有搜索到指定的值
    }

    // Overloading 方法的重载: Compile Time Polymorphism 编译时的多态性
    // 1. 重名，但是方法参数不同(类型和数目) !!!
    // 2. 可以有不同返回值, 不同限制Access, 抛出不同的异常类型
    public static int calculateScore(String playerName, int score) {
        System.out.println(playerName + " has score is " + score);
        return score * 100;
    }

    // 特例分析：如何声明一个能够交换两个变量值的方法            ========> C#区别: 使用ref和out关键字，以传引用的方式传递参数 !!
    // Java pass parameters by value 目前无法构建这样的方法 !!
    private static void swap(String a, String b) {
        String temp = a;
        a = b;
        b = temp;
    }
}
