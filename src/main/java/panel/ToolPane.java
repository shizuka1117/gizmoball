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

/**
 * ToolPane类，对GamePane中item进行旋转、删除、放大和缩小操作
 */
public class ToolPane extends JPanel {
    ToolActionListener toolActionListener = new ToolActionListener();
    public ToolPane(){
        setBackground(Color.white);
        setLayout(new GridLayout(2, 4));
        try {
            Properties kv = new IconUtil();
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/tool.properties"));
            Enumeration keys = kv.keys();
            ButtonGroup group=new ButtonGroup();
            while (keys.hasMoreElements()) {
                String iconName = keys.nextElement().toString();
                ImageIcon icon = new ImageIcon(kv.getProperty(iconName));
                icon.setImage(icon.getImage().getScaledInstance(40, 40,
                        Image.SCALE_AREA_AVERAGING));
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

    /**
     * ToolPane对应的Listener，用于修改组件
     */
    private class ToolActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame gameFrame = (GameFrame)getRootPane().getParent();
            GamePane gamePane = gameFrame.getGamePane();
            Item item = gamePane.getCurItem();
            //获取新加入的最后一个item，如果不为空则对其进行相应操作
            if(item!=null){
                switch (e.getActionCommand()){
                    case "rotate":
                        item.rotate();
                        break;
                    case "remove":
                        gamePane.remove(item);
                        break;
                    case "zoom-in":
                        item.enlarge();
                        break;
                    case "zoom-out":
                        item.reduce();
                        break;
                }
                gamePane.repaint();
            }
        }
    }
}
