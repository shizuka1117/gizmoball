package panel;
import util.IconUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.*;

/**
 * ItemPane类，用于添加组件
 */
public class ItemPane extends JPanel {
    IconUtil kv = new IconUtil();
    // 静态初始化用于显示组件列表
    {
        try {
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ItemPane() {
        Border titleBorder = BorderFactory.createTitledBorder("组件栏");
        ActionListener itemActionListener = new ItemActionListener();
        setBorder(titleBorder);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(5, 2));
            Enumeration<Object> keys = kv.keys();
            // 新建ButtonGroup，用于控制单选
            ButtonGroup group = new ButtonGroup();
            // 遍历枚举，将代表每个组件的JButton和图片添加到ItemPanel和ButtonGroup中
            while (keys.hasMoreElements()) {
                String iconName = keys.nextElement().toString();
                ImageIcon icon = kv.getImageIcon(iconName);
                JLabel label = new JLabel();
                label.setIcon(icon);
                JRadioButton button = new JRadioButton();
                button.setActionCommand(iconName);
                button.setBackground(Color.white);
                button.setPreferredSize(new Dimension(50, 50));
                button.setMargin(new Insets(5,20,0,5));
                button.addActionListener(itemActionListener);
                group.add(button);
                add(button);
                add(label);
            }
        setPreferredSize(new Dimension(100, 300));
    }

    /**
     * ItemPanel对应的Listener，用于处理RadiaButton点击事件，设置下一个要添加的item类型
     */
    private class ItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = e.getActionCommand();
            // 通过GameFrame在GamePane中设置下一个要新建的Item类名
            try {
                GameFrame gameFrame = (GameFrame) getRootPane().getParent();
                gameFrame.setNextItemName(itemName);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
