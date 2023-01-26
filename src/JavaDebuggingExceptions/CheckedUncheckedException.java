package JavaDebuggingExceptions;

import JavaDebuggingExceptions.checked.TestException;

// TODO. 对于可恢复的情况(强迫处理)使用checked exception; 对于编程错误使用unchecked exception
// - unchecked exception一般作用在程序错误"programming errors"
// - checked exception用于可以合理期望从异常中恢复，如果无法恢复则使用unchecked exception
public class CheckedUncheckedException {
    
    // 1. 如果程序抛出unchecked exception，则表示程序不可恢复，不可在运行下去
    //    用RuntimeException运行时异常来表明编程错误
    // The unchecked exception classes are the "run-time exception classes" and the "error classes"
    // RuntimeException and its subclasses + Error and its subclasses
    //    ArrayIndexOutOfBoundsException
    //    ClassCastException
    //    IllegalArgumentException
    //    IllegalStateException
    //    NullPointerException
    //    NumberFormatException
    //    ArithmeticException
    //    AssertionError
    //    ExceptionInInitializerError
    //    StackOverflowError
    //    NoClassDefFoundError
    //    NoSuchElementException

    // 2. 排除掉上面的两个部分的异常，其余所有的都是Checked Exception
    // The checked exception classes are all exception classes other than the unchecked exception classes
    // Throwable and all its subclasses, other than RuntimeException and its subclasses and Error and its subclasses
    //    无法忽略的异常(必须提供handler来处理)
    //    Exception
    //    IOException              IO异常
    //    FileNotFoundException    文件没有找到
    //    NoSuchFieldException
    //    ParseException           解析异常
    //    ClassNotFoundException   类型没有找到
    //    CloneNotSupportedException
    //    InstantiationException   实例化异常
    //    InterruptedException
    //    NoSuchMethodException
    //    SQLException
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                int result = thrower(arg);
                System.out.println("Test OK, no exception");
            } catch (Exception e) {
                System.out.println("threw message: " + e.getMessage());
            }
        }
    }

    static int thrower(String s) throws TestException {
        try {
            if (s.equals("divide")) {
                int i = 0;
                return i / i;  // Exception: java.lang.ArithmeticException 可以忽略的异常
            }
            if (s.equals("null")) {
                s = null;
                return s.length(); // Exception: java.lang.NullPointerException
            }
            if (s.equals("test")) {
                throw new TestException("Test message"); // 自定义的，不能够被忽略的异常
            }
            return 0;
        } finally {
            System.out.println("[thrower(\"" + s + "\") done]");
        }
    }
}
