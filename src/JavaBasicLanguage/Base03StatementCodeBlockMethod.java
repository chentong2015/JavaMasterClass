package JavaBasicLanguage;

public class Base03StatementCodeBlockMethod {

    public static void testStatement() {
        int myVariable = 50; // 整行就是一个Statement 中间的空格自动格式化
        System.out.println("This is a statement");
        System.out.println("This is " +
                "another"); // Statement支持换行 多行
        // indentation 代码的层级关系
    }

    public static void testCodeBlocks() {
        boolean gameOver = true;
        int score = 500;
        int levelCompleted = 5;
        int bonus = 100;
        // Block of code 完整的if语句
        if (score == 400) {
            System.out.println("your score is 400");
        } else if (score == 500) {
            System.out.println("your score is 500");
        } else {
            System.out.println("game over");
        }

        if (gameOver) {
            // 这里的变量finalScore仅仅作用在这个if声明语句块的作用域内部
            // 之后会被自动清理 不能在作用域Scope之外访问
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("your final score was =" + finalScore);
        }
    }

    // 1. java不支持设置方法参数的默认值          =======> 区别C#: 支持直接设置参数的默认值，同时支持ref & out关键字修饰 !!!!
    // 2. 方法的参数控制在3个之内, 一个抽象层级 !!!
    public static int testMethods(boolean gameOver, int score, int levelCompleted, int bonus) {
        // 内部会自动的创建局部变量
        // 在方法完成，返回的时候清除
        int position = 4; // 可以使用一个变量来作为返回的值 !!
        if (score >= 100) {
            return 1;
        } else if (score >= 50) { // 注意: 这一行的判断条件可以优化，排除到前面if出现的条件
            return 2;
        } else if (score >= 10) {
            return 3;
        } // 同样的 可以不用else
        // -1 通常是表示错误，或者是没有搜索到指定的值 !!!
        return -1;
    }

    // ******  Compile Time Polymorphism 编译时的多态性  *******
    // Overloading 方法的重载:
    // 1. 重名，但是方法参数不同(类型和数目)
    // 2. 可以有不同的返回值
    // 3. 可以有不同的限制Access
    // 4. 可以抛出不同的异常类型
    public static int calculateScore(String playerName, int score) {
        System.out.println(playerName + " has score is " + score);
        return score * 100;
    }

// 以下是非法方法
//    public static boolean calculateScore(String playerName, int score) {
//        System.out.println(playerName + " has score is " + score);
//        return true;
//    }

    public static int calculateScore(int score) {
        System.out.println("The score is " + score);
        return score * 100;
    }

    private static final String PREFIX_DATETIME = "0";

    public static String getFullDateTimeString(int minutes, int seconds) {
        if (!isValidMinutesAndSeconds(minutes, seconds)) {
            return "Invalid input";
        }
        String hourStr = formatDateTimeStrWithPrefixSuffix(minutes / 60, "h");
        String minuteStr = formatDateTimeStrWithPrefixSuffix(minutes % 60, "m");
        String secondStr = formatDateTimeStrWithPrefixSuffix(seconds, "s");
        return hourStr + minuteStr + secondStr;
    }

    private static boolean isValidMinutesAndSeconds(int minutes, int seconds) {
        return minutes >= 0 && seconds >= 0 && seconds < 60;
    }

    private static String formatDateTimeStrWithPrefixSuffix(int dateTime, String suffix) {
        String dateTimeStr = dateTime + suffix;  // 这里的int会自动的转成是String，再做链接
        return dateTime < 10 ? PREFIX_DATETIME + dateTimeStr : dateTimeStr;
    }

}
