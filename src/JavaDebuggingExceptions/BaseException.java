package JavaDebuggingExceptions;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 1. 处理异常的两种方式: LBYL & EAFP
 * 2. 异常的两种类别：checked (无法忽略) & unchecked
 * 3. Stack Track: All the methods calls at the point where program crashed) & Call back
 * 4. 异常的继承链: Throwable > Exception > RuntimeException > ...
 * <p>
 * TODO: Use checked exceptions for "recoverable conditions" and runtime exceptions for "programming errors"
 * 测试重写方法时候的异常声明
 *
 *
 * https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
 * https://stackoverflow.com/questions/6115896/understanding-checked-vs-unchecked-exceptions-in-java
 * https://stackoverflow.com/questions/1263128/most-common-checked-and-unchecked-java-exceptions
 *
 * https://stackoverflow.com/questions/3162760/differences-between-runtime-checked-unchecked-error-exception
 *
 * .
 * Unchecked Exception List ---------------------
 * ArrayIndexOutOfBoundsException
 * ClassCastException
 * IllegalArgumentException
 * IllegalStateException
 * NullPointerException
 * NumberFormatException
 * AssertionError
 * ExceptionInInitializerError
 * StackOverflowError
 * NoClassDefFoundError
 * .
 *
 *
 * Checked Exception List -----------------------
 * Exception
 * IOException
 * FileNotFoundException
 * ParseException
 * ClassNotFoundException
 * CloneNotSupportedException
 * InstantiationException
 * InterruptedException
 * NoSuchMethodException
 * NoSuchFieldException
 */
public class BaseException {

    private static int getIntFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    // 1. 提供更好的直观性和错误的来源, 一般适用于可定义的/可预见的错误
    // 2. LBYL is needed when the failure is expected to happen but rarely.
    // 3. LBYL are not atomic 不是原子(操作), 由于文件的判断和操作之间所存在的时间间隔, 造成异常
    private static int getIntLBYL() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        String inputStr = scanner.next();
        for (int i = 0; i < inputStr.length(); i++) {
            if (!Character.isDigit(inputStr.charAt(i))) {
                isValid = false;
                break;
            }
        }
        return isValid ? Integer.parseInt(inputStr) : 0;
    }

    // 1. EAFP is a good way to do validation
    // 2. 适用于IO操作, 确保操作过程的完成
    private static int getIntEAFP() {
        // try后面必须至少接一个catch或者是finally
        try {
            return new Scanner(System.in).nextInt();
        } catch (NoSuchElementException | ArithmeticException exception) {
            // 0. 尽可能的具体到指定的异常类型
            // 1. 可以同时捕获多个异常 -> Java 7之后，支持同时捕获
            // 2. 这里的代码尽量的简单，确保不会抛出新的异常
            // 3. 不要使用嵌套的异常捕获
            // 4. 重新抛出异常, 自定义输出的信息
            throw new ArithmeticException("No suitable input ");
        } finally {
            // 其中一定会执行的代码
        }
    }
}
