package JavaReflection;

import JavaReflection.model.BaseReflectionInterface;

public class JavaRefectionInterface implements BaseReflectionInterface {

    // 拿到类型实现的所有接口
    private void getClassInterfaces() throws ClassNotFoundException {
        Class clazz = Class.forName("com.example.main.ClassName");
        Class<?>[] interfaces = clazz.getInterfaces();
    }

    // TODO. 判断类型是否实现了某个接口，或者是某个接口的实现
    // - instanceof 判断实例对象是否是某种类型
    // - isAssignableFrom 判断接口是否有指定的继承类型
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("JavaReflection.JavaRefectionInterface");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        if (instance instanceof BaseReflectionInterface) {
            System.out.println("check instanceof");
        }
        if (BaseReflectionInterface.class.isAssignableFrom(clazz)) {
            System.out.println("check AssignableFrom");
        }
    }
}
