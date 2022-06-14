package JavaReflection;

public class JavaRefectionInterface {

    // 拿到类型实现的所有的接口
    private void getClassInterfaces() throws ClassNotFoundException {
        // Loads the specified class using class.forName
        Class clazz = Class.forName("com.example.main.ClassName");
        Class<?>[] interfaces = clazz.getInterfaces();
    }
}
