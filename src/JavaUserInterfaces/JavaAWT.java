package JavaUserInterfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Abstract Window Toolkit 用户界面的开发
 * JavaAWT myWindow = new JavaAWT("Java Course");
 * myWindow.setVisible(true);
 */
public class JavaAWT extends Frame {

    public JavaAWT(String title) throws HeadlessException {
        super(title);
        setSize(500, 200);
        // 匿名类型，实现了WindowListener接口的类型
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
            //如果重写windowClosed方法，app会继续在后台运行 !!!
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font fontNormal = new Font("SansSerif", Font.BOLD, 18);
        g.setFont(fontNormal);
        g.drawString("The complete java course", 60, 60);

        Font fontSmall = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(fontSmall);
        g.drawString("chen tong", 60, 100);

        Button clickButton = new Button("click button");
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked ");
            }
        });
    }
}
