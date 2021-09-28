package JavaUserInterfaces.swing;

import javax.swing.*;

// Swing: Java原生用户界面开发, 结合Awt一起使用
// https://docs.oracle.com/javase/tutorial/uiswing/index.html
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
