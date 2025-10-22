package JavaReflection.exception;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// TODO. Refection反射调用方法时常见的两种异常
//  通过反射调用方法可能需要对抛出的异常做进一步操作(解析Cause)
public class InvocationTargetExceptionDemo {

    private static class InvocationTargetExample {
        public int divideByZero() {
            return 1 / 0;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        InvocationTargetExample targetExample = new InvocationTargetExample();
        Method method = InvocationTargetExample.class.getMethod("divideByZero");
        method.setAccessible(true);
        try {
            method.invoke(targetExample);
        } catch (IllegalAccessException e) {
            // 表示指定的反射方法无法invoke调用
        } catch (InvocationTargetException exception) {
            // Refection反射会将调用的原始方法抛出的异常封装到InvocationTargetException
            // 通过获取Cause能够拿到底层方法抛出的异常
            Throwable throwable = exception.getCause();
        }
    }
}
