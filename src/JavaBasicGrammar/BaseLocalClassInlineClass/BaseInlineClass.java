package JavaBasicGrammar.BaseLocalClassInlineClass;

import JavaBasicGrammar.BaseLocalClassInlineClass.model.ButtonView;
import JavaBasicGrammar.BaseLocalClassInlineClass.model.OnClickListener;

// Inline class 匿名类: 没有名称，通常可以使用lambda表达式替换(唯一实现的方法)
// 1. An anonymous class has access to the members of its enclosing class.
// 2. An anonymous class cannot access local variables in its enclosing scope
//    that are not declared as final or effectively final (程序上确定不去改动)
public class BaseInlineClass {

    private String classFieldVariable;

    private void testAnonymousClass() {
        // TODO: 被捕获的变量必须声明成final，避免造成数据同步的问题
        // Java在实现匿名类型的时候，会将使用的局部变量作为private filed成员，同时Copy变量的原始值
        // 如果允许变量改变，则在实际使用变量的值时，则无法保证数据的同步更新
        final String localVariable = "local variable";

        // 将申明的匿名类型作为参数传递
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
            }
        });
        baseLocalClassPrint.onMove();
    }
}
