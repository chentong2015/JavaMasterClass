package JavaUserInterfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// AWT: Abstract Window Toolkit 原生的跨平台GUI工具包，效果原始且不够快 ==> Swing作为发展版本
// SWT: Standard Widget Toolkit IBM开发的增强型GUI工具包 org.eclipse.swt
public class BaseAwtSwt extends Frame {

    // UI Element属性变化驱动事件PropertyChangeEvent, PropertyChangeListener

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
