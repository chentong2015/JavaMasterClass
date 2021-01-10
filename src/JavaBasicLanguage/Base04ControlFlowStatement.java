package JavaBasicLanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * Java控制语句
 * 1. Switch
 * 2. For / "Foreach"
 * 3. While / Do While
 */
public class Base04ControlFlowStatement {

    public static void testSwitchStatement(int switchValue) {
        // switch 中用于判断的类型一般是Primitive Type
        switch (switchValue) {
            case 1:
                System.out.println("Find 1");
                break; // 跳出switch的判断 跳出 ==> 没有break，程序将会继续往后执行 !!
            case 2:
                System.out.println("Find 2");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Find 3 or 4 or 5"); // 综合以上几种case在一起
                break;
            default:
                System.out.println("Find others ");
        }
    }

    public static void testSwitchStatement2() {
        String month = "january";
        switch (month.toLowerCase()) {  // switch可以直接调用方法
            case "January":
                System.out.println("Find Jan");
                break;
            case "June":
                System.out.println("Find June");
                break;
            default:
                System.out.println("Find nothing");
        }
    }

    public static void testForStatement() {
        // 初始化 终结条件 增减值
        // 在for循环结束的时候 创建的零时变量 将会被清理
        for (int index = 0; index < 100; index++) {
            System.out.println("Find " + index);
        }

        // 判断质数的算法
        int checkNum = 107;
        boolean isPrime = true;
        if (checkNum == 1) {
            isPrime = false;
        } else {
            //  checkNum / 2 ==> 优化算法 Math.sqrt(checkNum) 取平方根导致循环的次数减少 !!
            for (int i = 2; i <= (long) Math.sqrt(checkNum); i++) {
                if (checkNum % i == 0) {
                    isPrime = false;
                }
            }
        }
    }

    // 针对于能够被迭代的集合
    public static void testForeachStatement() {   // ===========> 区别C#：foreach(var item in collection) {}  !!!!
        int[] testArray = new int[10];
        for (int intItem : testArray) {
            System.out.println("Find int value: " + intItem);
        }

        List<String> testArrayList = new ArrayList<>();
        for (String strItem : testArrayList) {
            System.out.println("Find string value: " + strItem);
        }
    }

    public static void testWhileStatement() {
        int count = 0;
        while (count < 5) {
            System.out.println("continue: " + count);
            count++;
        }

        count = 1;
        while (true) {
            if (count == 5) {
                break;
            }
            System.out.println("continue" + count);
            count++;
        }
    }

    public static void testDoWhileStatement() {
        // Do while至少执行一次
        int count = 1;
        do {
            System.out.println("continue" + count);
            count++;
            if (count > 100) {
                break;
            }
            // continue 继续下一次循环
        } while (count != 6);
    }
}
