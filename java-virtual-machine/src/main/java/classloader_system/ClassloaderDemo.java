package classloader_system;

public class ClassloaderDemo {

    public static void main(String[] args) {
        // 拿到指定类型的类加载器，String类型默认由BootStrap类加载器加载
        System.out.println(String.class.getClassLoader());

        // classLoader.getClass().getName();
        // sum.misc.Launcher$AppClassLoader
        // AppClassLoader是声明在启动器Launcher类中的静态内部类

        // 获取系统默认的类加载器$AppClassLoader
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        // 返回上级类加载器
        classLoader.getParent();

        // 通过类加载器获取加载的资源, 返回的资源可能是文件或者目录
        classLoader.getResource("com/example/service");
    }
}
