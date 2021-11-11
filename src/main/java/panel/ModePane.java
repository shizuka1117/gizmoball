package panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ModePane extends JPanel {
    public ModePane() {
        setBackground(Color.white);
        Border titleBorder = BorderFactory.createTitledBorder("模式栏");
        setBorder(titleBorder);
        setPreferredSize(new Dimension(100, 100));
        JButton button1 = new JButton("布局模式");
        button1.setMargin(new Insets(0,0,0,0));
        button1.setPreferredSize(new Dimension(80, 20));
        JButton button2 = new JButton("游玩模式");
        button2.setPreferredSize(new Dimension(80, 20));
        button2.setMargin(new Insets(0,0,0,0));
        add(button1);
        add(button2);
    }
}
