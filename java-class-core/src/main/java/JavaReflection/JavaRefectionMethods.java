package JavaReflection;

import JavaBasicOOP.Annotation.ClassPreamble;
import JavaReflection.model.BaseReflectionClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaRefectionMethods {

    // TODO. 通过反射创建类型实例，调用指定名称的方法(传递指定类类型的参数)
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("JavaReflection.model.BaseReflectionClass");
        System.out.println(cls.getPackageName());

        // 需要创建实例对象才能通过放射调用方法
        BaseReflectionClass baseReflectionClass = (BaseReflectionClass) cls.newInstance();

        // 这里传递的参数必须类型匹配，否则抛出如下异常
        // java.lang.IllegalArgumentException: argument type mismatch
        Method method = cls.getMethod("printString", String.class);
        method.invoke(baseReflectionClass, false);
    }

    // Retrieve the list of methods defined in the class
    private void getClassMethodInfos() throws ClassNotFoundException {
        Class clazz = Class.forName("com.example.main.ClassName");
        Method[] methods = clazz.getDeclaredMethods();
        Method checkMethod = methods[0];
        Class[] parameterTypes = checkMethod.getParameterTypes();
        Class[] exceptionTypes = checkMethod.getExceptionTypes();
        Class returnType = checkMethod.getReturnType();
    }

    // .getMethod() 通过方法名称和提供的参数查找指定的方法
    // method.invoke() 必须在适当类型的对象实例上被调用
    private void testInvokingMethodByName() throws ClassNotFoundException, NoSuchMethodException {
        Class cls = Class.forName("BaseReflectionClass");
        Class[] parameterTypes = new Class[2];
        parameterTypes[0] = Integer.TYPE;
        parameterTypes[1] = Integer.TYPE;
        Method method = cls.getMethod("add", parameterTypes);

        BaseReflectionClass invokeObject = new BaseReflectionClass();
        Object[] args = {10, 10};
        try {
            method.invoke(invokeObject, args);
        } catch (IllegalAccessException e) {
            System.out.println("Can not access the method");
        } catch (InvocationTargetException exception) {
            System.out.println("Invocation target method exception");
        }
    }

    // 通过反射拿到添加在Method方法上的注解
    private void resolveMethodAnnotation(Method method) {
        // Class<? extends Annotation> type; 可以使用泛型来表示要获取的注解类型(的种类)
        Annotation annotation = method.getAnnotation(ClassPreamble.class);
        if (method.isAnnotationPresent(ClassPreamble.class)) {
            try {
                method.invoke(null);
            } catch (IllegalAccessException e) {
                System.out.println("Can not access the method");
            } catch (InvocationTargetException exception) {
                System.out.println("Invocation target method exception");
            }
        }
    }
}
