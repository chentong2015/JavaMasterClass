package JavaDebuggingExceptions;

import JavaDebuggingExceptions.model.TestException;

/**
 * 异常的(层级)继承链
 * Throwable > Exception > RuntimeException / Error
 */
public class CheckedUncheckedException {

    // The unchecked exception classes are the "run-time exception classes" and the "error classes".
    // RuntimeException and its subclasses and Error and its subclasses.
    /**
     * 2. Unchecked Exception: 可忽略的异常, 一般作用在程序的错误上 "programming errors"
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
     */

    // 排除掉上面的两个部分的异常，其余所有的都是Checked Exception
    // The checked exception classes are all exception classes other than the unchecked exception classes.
    // Throwable and all its subclasses, other than RuntimeException and its subclasses and Error and its subclasses.

    /**
     * 1. Checked Exception: 无法忽略的异常(必须提供handler来处理), 一般作用在可恢复的条件上 "recoverable conditions"
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

    public static void main(String[] args) {
        for (String arg : args) {
            try {
                int result = thrower(arg);
                System.out.println("Test OK, \"" + arg + "\" didn't throw an exception");
            } catch (Exception e) {
                System.out.println("Test \"" + arg + "\" threw message: " + e.getMessage());
            }
        }
    }

    static int thrower(String s) throws TestException {
        try {
            if (s.equals("divide")) {
                int i = 0;
                return i / i;
            }
            if (s.equals("null")) {
                s = null;
                return s.length();
            }
            if (s.equals("test")) {
                throw new TestException("Test message");
            }
            return 0;
        } finally {
            System.out.println("[thrower(\"" + s + "\") done]");
        }
    }
}
