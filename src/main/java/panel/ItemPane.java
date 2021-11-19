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

public class ItemPane extends JPanel {
    //保存每个item的名称和对应的icon存储位置
    IconUtil kv = new IconUtil();
    public ItemPane() {
        ItemActionListener itemActionListener = new ItemActionListener();
        Border titleBorder = BorderFactory.createTitledBorder("组件栏");
        setBorder(titleBorder);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(5, 2));
        try {
            //获取properties文件的流对象
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));
            Enumeration<Object> keys = kv.keys();
            ButtonGroup group=new ButtonGroup();
            //遍历枚举
            while (keys.hasMoreElements()) {
                //取出每个Key
                String iconName = keys.nextElement().toString();
                //根据key获取value
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(100, 300));
    }

    private class ItemActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = e.getActionCommand();
            //获取gamePane，设置nextItemName（下一个要新建的Item类名）
            try {
                GameFrame gameFrame = (GameFrame) getRootPane().getParent();
                gameFrame.setNextItemName(itemName);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
