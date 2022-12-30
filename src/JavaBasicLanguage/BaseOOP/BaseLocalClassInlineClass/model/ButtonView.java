package JavaBasicLanguage.BaseOOP.BaseLocalClassInlineClass.model;

public class ButtonView {

    private String title;
    // 关联在button上的点击事件监听器
    private OnClickListener onClickListener;

    public ButtonView(String title) {
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
}
