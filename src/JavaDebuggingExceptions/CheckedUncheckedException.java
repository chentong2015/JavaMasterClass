package JavaDebuggingExceptions;

import JavaDebuggingExceptions.model.TestException;

// 1. 异常的(层级)继承链:
//    Throwable > Exception (期望恢复) > RuntimeException
//    Throwable > Error (不期望恢复)
// 2. 使用规则
//    2.1 不要抛出RuntimeException异常，不要创建它的任何SubClass
//    2.2 unchecked exception一般作用在程序错误"programming errors"
//    2.3 如果可以合理地期望Client从异常中恢复，则使用checked exception
//        如果Client无法做到恢复，则使用unchecked exception
public class CheckedUncheckedException {

    // 1. The unchecked exception classes are the "run-time exception classes" and the "error classes"
    //    RuntimeException and its subclasses + Error and its subclasses
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
    //    The checked exception classes are all exception classes other than the unchecked exception classes
    //    Throwable and all its subclasses, other than RuntimeException and its subclasses and Error and its subclasses
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
