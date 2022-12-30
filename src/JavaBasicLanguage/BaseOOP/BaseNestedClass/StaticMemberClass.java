package JavaBasicLanguage.BaseOOP.BaseNestedClass;

public abstract class StaticMemberClass {

    protected String outsideName;

    // 静态的方法中不能调用非静态的成员
    private static String getName() {
        // return outsideName;
        return "null";
    }

    // TODO. 静态成员类时外围类的一个静态成员，和其他静态成员一样，不算是一种"inner class"
    static class InnerClass {
        public void testOutsideName() {
            // 不能直接调用外部的非静态属性
            // outsideName = "";

            // 可以调用外部静态的方法
            getName();
        }
    }
}
