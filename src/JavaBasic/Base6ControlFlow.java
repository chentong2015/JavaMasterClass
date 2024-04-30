package JavaBasic;

import java.util.ArrayList;
import java.util.List;

public class Base6ControlFlow {

    // TODO: 注意JDK14之后的Switch语句
    // switch判断类型一般为Primitive Type(int, String类型...)
    // switch不能判断long类型(非精确类型)
    // switch不能判断null(抛出NullPointerException)
    public static void testSwitchStatement(int switchValue) {
        String value = null;
        switch (value) {
            case "A":
                System.out.println("AA");
            case "B":
                System.out.println("BB");
        }

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

    // 使用break可以跳出多重嵌套循环: 最外层循环前加一个标记如A，然后用break A
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

    // init初始化-condition终结条件-loop增减值
    // 在for循环结束的时候, 创建的零时变量将会被清理
    public static void testForStatement() {
        // 支持多个遍历的初始和loop循环
        int i1, j1;
        for (i1 = 5, j1 = 10; i1 + j1 < 20; i1++, j1++) {
            System.out.println("i1 + j1 =" + (i1 + j1));
        }

        // loop循环时可以执行其他操作
        for (int index = 0; index < 100; index++, System.out.println("index")) {
            System.out.println("Find " + index);
        }
    }

    // 对集合数据的for-each遍历
    public static void testForeachStatement() {
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
