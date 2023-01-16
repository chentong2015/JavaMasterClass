package JavaBasicLanguage.BaseOOP.BaseLocalClassInlineClass;

import JavaBasicLanguage.BaseOOP.BaseLocalClassInlineClass.model.ButtonView;
import JavaBasicLanguage.BaseOOP.BaseLocalClassInlineClass.model.OnClickListener;

// Anonymous Class 匿名类在申明时的约束
// 1. An anonymous class cannot have an explicitly declared constructor.
// 2. Java compiler must automatically provide an anonymous constructor for the anonymous class
// https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.9.5.1
public class BaseAnonymousClass {

    public void testAnonymousClass() {
        ButtonView buttonView = new ButtonView("test view");
        buttonView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(String title) {
            }

            @Override
            public void onMove() {
            }
        });
    }

}
