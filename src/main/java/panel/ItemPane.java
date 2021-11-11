package panel;

import item.Item;
import util.IconUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

public class ItemPane extends JPanel {
    //保存每个item的名称和对应的icon存储位置
    ItemActionListener itemActionListener = new ItemActionListener();
    GamePane gamePane;
    public ItemPane(GamePane gamePane) {
        this.gamePane=gamePane;
        Border titleBorder = BorderFactory.createTitledBorder("组件栏");
        setBorder(titleBorder);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(5, 2));
        try {
            //获取properties文件的流对象
            Properties kv = new IconUtil();
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));
            Enumeration<Object> keys = kv.keys();
            ButtonGroup group=new ButtonGroup();
            //遍历枚举
            while (keys.hasMoreElements()) {
                //取出每个Key
                String iconName = keys.nextElement().toString();
                //根据key获取value
                ImageIcon icon = new ImageIcon(kv.getProperty(iconName));
                icon.setImage(icon.getImage().getScaledInstance(25, 25,
                        Image.SCALE_DEFAULT));
                JLabel label = new JLabel();
                label.setIcon(icon);
                JRadioButton button = new JRadioButton();
                button.addActionListener(itemActionListener);
                button.setActionCommand(iconName);
                button.setBackground(Color.white);
                button.setPreferredSize(new Dimension(50, 50));
                button.setMargin(new Insets(5,20,0,5));
                group.add(button);
                add(button);
                add(label);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(100, 300));
//        setSize(100, 250);
    }

    private class ItemActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //反射创建对象
            try {
                //TODO:修改调用的构造器，创建不同类型的item
                Class<Item> onClass = (Class<Item>) Class.forName("item."+e.getActionCommand());
                Constructor<Item> constructor=onClass.getDeclaredConstructor();
                Item item = constructor.newInstance();
                gamePane.setCurItem(item);
                gamePane.addItem(item);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
