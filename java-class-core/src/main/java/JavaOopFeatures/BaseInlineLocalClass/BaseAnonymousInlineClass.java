package JavaOopFeatures.BaseInlineLocalClass;

import JavaOopFeatures.BaseInlineLocalClass.model.ButtonView;
import JavaOopFeatures.BaseInlineLocalClass.model.OnClickListener;

// Inline class 匿名类
// - 仅在使用时被声明和实例化
// - 匿名类型出现在表达式中，必须保持短小(替换成lambda表达式)
public class BaseAnonymousInlineClass {

    // An anonymous class has access to the members of its enclosing class.
    private String classFieldVariable;
    private static String staticFieldVariable;

    // TODO. 匿名类出现在静态方法中，可以方法静态的属性
    private static void testAnonymousClassInsideStaticMethod() {
        final String localVariable = "local variable";

        ButtonView baseLocalClassPrint = new ButtonView("Print button");
        baseLocalClassPrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was called ");
            }

            @Override
            public void onMove() {
                System.out.println(localVariable.equals("new"));
                System.out.println(staticFieldVariable);
            }
        });
    }

    // TODO: 匿名类出现在非静态方法中，它才有外围实例
    // Java在实现匿名类型的时候，会将使用的局部变量作为private filed成员，同时Copy变量的原始值
    // 被捕获的变量必须声明成final或者effectively final，避免造成数据同步的问题
    // Variable 'localVariable' is accessed from within inner class, needs to be final or effectively final
    private void testAnonymousClass() {
        // An anonymous class cannot access local variables in its enclosing scope
        // that are not declared as final or effectively final
        final String localVariable = "local variable";

        ButtonView baseLocalClassPrint = new ButtonView("Print button");
        baseLocalClassPrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was called ");
            }

            @Override
            public void onMove() {
                System.out.println(localVariable);
                System.out.println(classFieldVariable);
                System.out.println(staticFieldVariable);
            }
        });
        baseLocalClassPrint.onMove();
    }
}
