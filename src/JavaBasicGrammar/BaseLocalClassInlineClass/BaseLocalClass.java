package JavaBasicGrammar.BaseLocalClassInlineClass;

import JavaBasicGrammar.BaseLocalClassInlineClass.model.ButtonView;
import JavaBasicGrammar.BaseLocalClassInlineClass.model.OnClickListener;

// Local class 内部类: inner class defined inside of a scope
// 在方法中声明的局部类型, 类型不会在被的地方使用, 作用域Scope只是在声明的方法内部 !!!
public class BaseLocalClass {

    public void testLocalClass() {
        // 申明了实现指定接口的内部类，类型具有指定的名称
        class ButtonClickListener implements OnClickListener {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked");
            }

            @Override
            public void onMove() {
                System.out.println("Test on move");
            }
        }
        // 创建指定类型的对象
        ButtonClickListener buttonClickListener = new ButtonClickListener();

        // 将内部类的实例对象作为参数传递
        ButtonView baseLocalClassPrint = new ButtonView("Title");
        baseLocalClassPrint.setOnClickListener(buttonClickListener);
        baseLocalClassPrint.onClick();
    }
}
