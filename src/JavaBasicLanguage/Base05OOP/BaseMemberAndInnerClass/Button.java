package JavaBasicLanguage.Base05OOP.BaseMemberAndInnerClass;

public class Button {

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

    // Inner interface (嵌套接口) 只关联Button的内部接口
    public interface OnClickListener {
        void onClick(String title);

        void onMove();
    }

    /**
     * 1. public 不能作用在Local Class和anonymous class上面 !!
     * 2. protected & private 不能作用在Local Class和anonymous class上面 !!
     */
    public static void main(String[] args) {
        //  Local class : inner class defined inside of a scope
        //  在方法中声明的局部类型, 类型不会在被的地方使用 !! 作用域Scope只是在声明的方法内部 !!!
        class ButtonClickListener implements Button.OnClickListener {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked");
            }

            @Override
            public void onMove() {
                System.out.println("Test on move");
            }
        }
        Button buttonPrint = new Button("Print button");
        ButtonClickListener buttonClickListener = new ButtonClickListener();
        buttonPrint.setOnClickListener(buttonClickListener);
        // 按钮被点击 触发Listener.onClick()方法
        buttonPrint.onClick();

        // Inline class 匿名类型: class 没有名称，全部的声明都在 {} 之中 ====> 通常可以使用lambda表达式替换(唯一实现的方法)
        buttonPrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was called ");
            }

            @Override
            public void onMove() {
                System.out.println("Test on move");
            }
        });
        buttonPrint.onMove();
    }
}
