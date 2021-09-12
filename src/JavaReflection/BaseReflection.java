package JavaReflection;

import JavaBasicLanguage.Base04Annotation.ClassPreamble;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

// Java Reflection:
// Java语言的一个特性，比如支持一个Java Class获取和操作它内部所有成员 ==> Pascal, C, or C++没有这样的特性
// It supports dynamic retrieval of information about classes and data structures by name
// Allows for their manipulation within an executing Java program
public class BaseReflection {

    public static void main(String[] args) throws ClassNotFoundException {
        // Loads the specified class using class.forName
        Class clazz = Class.forName(args[0]);
        // 拿到类型实现的所有的接口
        Class<?>[] interfaces = clazz.getInterfaces();

        // 由于类型声明的构造器可能不止一个，可通过参数列表来获取指定的构造器
        Constructor ctorlist[] = clazz.getDeclaredConstructors();
        // 判断加载的类型是否是某个类型的实例
        boolean isInstance = clazz.isInstance(new BaseReflection());

        // TODO: Class Fields的判断和处理
        Field[] fieldList = clazz.getDeclaredFields();
        Field checkFiled = fieldList[0];
        String fieldName = checkFiled.getName();
        Type fieldType = checkFiled.getType();
        String fieldTypeName = fieldType.getTypeName();
        int modifierId = checkFiled.getModifiers();
        String modifierName = Modifier.toString(modifierId);

        // TODO: Methods方法的判断和处理
        // Retrieve the list of methods defined in the class
        Method[] methods = clazz.getDeclaredMethods();
        Method checkMethod = methods[0];
        Class[] parameterTypes = checkMethod.getParameterTypes();
        Class[] exceptionTypes = checkMethod.getExceptionTypes();
        Class returnType = checkMethod.getReturnType();
    }

    // .getMethod()    通过方法名称和提供的参数查找指定的方法
    // method.invoke() 必须在适当类型的对象实例上被调用
    private void testInvokingMethodByName() throws ClassNotFoundException, NoSuchMethodException {
        Class cls = Class.forName("BaseReflection");
        Class[] parameterTypes = new Class[2];
        parameterTypes[0] = Integer.TYPE;
        parameterTypes[1] = Integer.TYPE;
        Method method = cls.getMethod("add", parameterTypes);

        BaseReflection invokeObject = new BaseReflection();
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

    // TODO: 通过对象的Constructor构造器来创建新的对象, 调用Constructor的.newInstance()方法
    private void testCreatingNewObject() {
        try {
            Class cls = Class.forName("constructor2");
            Class parameterTypes[] = new Class[2];
            parameterTypes[0] = Integer.TYPE;
            parameterTypes[1] = Integer.TYPE;
            // 拿到具有特定参数的构造器
            Constructor ct = cls.getConstructor(parameterTypes);
            // 通过传递指定的参数创建对象
            Object newInstance = ct.newInstance(new int[]{10, 10});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testChangingFieldValue() {
        BaseReflection invokeObject = new BaseReflection();
        try {
            Class cls = Class.forName("BaseReflection");
            Field fld = cls.getField("d");
            fld.setDouble(invokeObject, 12.34);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 使用反射类型的数组对象
    private void testUsingArrays() {
        try {
            Class cls = Class.forName("java.lang.String");
            Object arr = Array.newInstance(cls, 10);
            Array.set(arr, 5, "value");
            String s = (String) Array.get(arr, 5);
            System.out.println(s);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
