package JavaReflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

public class JavaRefectionConstructor {

    // 由于类型声明的构造器可能不止一个，可通过参数列表来获取指定的构造器
    public void getClassConstructor() throws ClassNotFoundException {
        Class clazz = Class.forName("com.example.main.ClassName");
        Constructor ctorlist[] = clazz.getDeclaredConstructors();
    }

    // 通过对象的Constructor构造器来创建新的对象, 调用Constructor的.newInstance()方法
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
