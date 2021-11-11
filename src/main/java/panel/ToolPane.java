package panel;

import item.Item;
import util.IconUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ToolPane extends JPanel {
    GamePane gamePane;
    ToolActionListener toolActionListener = new ToolActionListener();
    public ToolPane(GamePane gamePane){
        this.gamePane=gamePane;
        setBackground(Color.white);
        setLayout(new GridLayout(2, 4));
        try {
            //获取properties文件的流对象
            Properties kv = new IconUtil();
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/tool.properties"));
            Enumeration keys = kv.keys();
            //遍历枚举
            ButtonGroup group=new ButtonGroup();
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
                button.setBackground(Color.white);
                button.addActionListener(toolActionListener);
                button.setActionCommand(iconName);
                button.setPreferredSize(new Dimension(50, 50));
                button.setMargin(new Insets(5,20,0,10));
                group.add(button);
                add(button);
                add(label);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Border titleBorder = BorderFactory.createTitledBorder("工具栏");
        setBorder(titleBorder);
        setPreferredSize(new Dimension(100, 150));
    }

    private class ToolActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "rotate":
                    break;
                case "remove":break;
                case "zoom-in":break;
                case "zoom_out":break;
            }
        }
    }
}