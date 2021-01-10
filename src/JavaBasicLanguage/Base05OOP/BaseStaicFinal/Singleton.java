package JavaBasicLanguage.Base05OOP.BaseStaicFinal;

public class Singleton {

    private static int ID;
    protected String name;
    private static Singleton instance = null;

    // 不能在类型外部被调用的构造器
    private Singleton() {
    }

    public static Singleton Instance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}


