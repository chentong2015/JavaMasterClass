package JavaBasicLanguage.Base05OOP.BaseMemberAndInnerClass;

import JavaBasicLanguage.Base05OOP.BaseMemberAndInnerClass.Model.OnClickListener;

/**
 * 1. public 不能作用在Local Class和anonymous class上面 !!
 * 2. protected & private 不能作用在Local Class和anonymous class上面 !!
 */
public class Button {

    private String classFieldVariable;
    private String title;
    private OnClickListener onClickListener;

    public Button(String title) {
        this.title = title;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick() {
        this.onClickListener.onClick(title);
    }

    public void onMove() {
        this.onClickListener.onMove();
    }

    /**
     * Local class : inner class defined inside of a scope
     * 在方法中声明的局部类型, 类型不会在被的地方使用, 作用域Scope只是在声明的方法内部 !!!
     */
    public void testLocalClass() {
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
        Button buttonPrint = new Button("Title");
        ButtonClickListener buttonClickListener = new ButtonClickListener();
        buttonPrint.setOnClickListener(buttonClickListener);
        buttonPrint.onClick();
    }

    /**
     * Inline class: 没有名称 ====> 通常可以使用lambda表达式替换(唯一实现的方法)
     * 1. An anonymous class has access to the members of its enclosing class. 可以访问当前类型的成员
     * 2. An anonymous class cannot access local variables in its enclosing scope that are not declared as final or effectively final(程序上确定不去改动).
     * Java在实现匿名类型的时候，会将使用的局部变量作为private filed成员, 同时Copy变量的原始值
     * 被捕获的变量必须声明成final，避免造成数据同步的问题: 如果允许变量改变，则在实际使用变量的值时，则无法保证数据的同步更新
     */
    private void testAnonymousClass() {
        final String localVariable = "local variable";
        Button buttonPrint = new Button("Print button");
        buttonPrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was called ");
            }

            @Override
            public void onMove() {
                System.out.println(classFieldVariable);
                System.out.println(localVariable);
            }
        });
        buttonPrint.onMove();
    }
}
