package JavaBasicLanguage.BaseOOP.BaseNestedClass;

import java.util.function.Function;

public abstract class StaticMemberClass {

    protected String outsideName;

    // 静态的方法中不能调用非静态的成员
    private static String getName() {
        // return outsideName;
        return "null";
    }

    // TODO. 静态成员类是外围类的一个静态成员，和其他静态成员一样，不是"inner class"
    static class InnerClass {
        public void testOutsideName() {
            // 不能直接调用外部的非静态属性
            // outsideName = "";

            // 可以调用外部静态的方法
            getName();
        }
    }

    // 抽象类中的公开静态类，和非抽象类中的公开静态类创建对象方式一致
    // StaticMemberClass.InnerPublicClass innerClass = new StaticMemberClass.InnerPublicClass();
    public static class InnerPublicClass<R> {

        private Function<String, R> function;
    }
}
