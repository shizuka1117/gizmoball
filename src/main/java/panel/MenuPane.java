package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

public class MenuPane extends JMenuBar {

    public MenuPane(){
        JMenu menu = new JMenu("文件");
        JMenuItem menuItem1 = new JMenuItem("新建游戏");
        menuItem1.setActionCommand("new");
        JMenuItem menuItem2 = new JMenuItem("保存游戏");
        menuItem2.setActionCommand("save");
        JMenuItem menuItem3 = new JMenuItem("读取游戏");
        menuItem3.setActionCommand("load");
        MenuClickListener menuClickListener = new MenuClickListener();
        menuItem1.addActionListener(menuClickListener);
        menuItem2.addActionListener(menuClickListener);
        menuItem3.addActionListener(menuClickListener);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        add(menu);
    }

    private class MenuClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String menuItemName = e.getActionCommand();
            //TODO:处理不同的menu事件
            switch (menuItemName){
                case "new":
                    System.out.println("new"); break;
                case "save":break;
                case "load":break;
            }
        }
    }
}
