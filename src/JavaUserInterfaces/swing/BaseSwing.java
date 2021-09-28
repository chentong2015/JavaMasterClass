package JavaUserInterfaces.swing;

import javax.swing.*;

// https://docs.oracle.com/javase/tutorial/uiswing/index.html
// Swing基本组件Components
// 1. TopLevel Container: JFrame, JDialog, and JApplet
// 2. JComponent: 提供一些特性
// 3. Text Components: UI界面文本的显示
// 4. HTML Tags
// 5. Models
// 6. Icons
public class BaseSwing {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setSize(200, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
