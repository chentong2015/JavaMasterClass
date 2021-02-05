package JavaLambdaExpressions;

import JavaLambdaExpressions.Model.IStringUpperConcat;

/**
 * 1. 优化只有一个方法的接口的方法调用             ====> C#区别：Lambda表达式是一种匿名方法
 * 2. 优化只有一个方法的(匿名)类型的方法调用
 */
public class BaseLambdaExpressions {

    private static void testLambdaExpressions() {
        // 1. 使用类型实例 new Thread(new CodeToRun()).start();

        // 2. 使用匿名类型
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf(getClass().getSimpleName()); // 匿名类型，没有class名称
                System.out.println("Printing runnable");
            }
        }).start();

        // 3. 使用Lambda表达式
        // 编译器背后的逻辑：Thread()接受一个实现了Runnable接口的类型实例
        // 由于接口中只含有一个public abstract void run(); 抽象方法，且方法不具备输入参数，也不输出参数
        // 于是编译器将Lambda表达式match(maps to)到该方法，完成对方法的"实现"
        new Thread(() -> {
            System.out.println("Printing ...");
            System.out.format("This is line %d \n", 3);
        }).start();
    }

    /**
     * 将Lambda表达式赋值给接口 = () -> {}
     * 1. () 其中传递的参数会做出类型推断，根据实际参数确定，如果只有一个参数，则可以不写括号
     * 2. {} 主体中申明单个的Statement返回，则return可以不写, 如果有多个Statement，则需要写
     */
    public static void testString() {
        IStringUpperConcat strUpperConcat = (str1, str2) -> str1 + str2;
        System.out.printf(doString(strUpperConcat, "", ""));

        IStringUpperConcat stringUpperConcat2 = ((str1, str2) -> {
            String result = str1.toUpperCase() + str2.toUpperCase();
            return result;
        });
    }

    public final static String doString(IStringUpperConcat uc, String str1, String str2) {
        return uc.upperAndConcat(str1, str2);
    }
}

