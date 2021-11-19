package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
            GameFrame gameFrame = (GameFrame) getRootPane().getParent();
            GamePane gamePane = gameFrame.getGamePane();
            //处理不同的menu事件
            switch (menuItemName){
                case "new":
                    System.out.println("new");
                    for (Component c:gamePane.getComponents()) {
                        gamePane.remove(c);
                    }
                    gamePane.setCurItem(null);
                    //需要手动立即更新UI，否则删除的组件仍会显示
                    gamePane.updateUI();
                    break;
                case "save":
                    saveGame();
                    break;
                case "load":
                    //完成读取功能
                    loadGame();
                    break;
            }
        }
        public void saveGame(){
            //保存时自动应用设计模式，停止动画
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(null);
            if(option==JFileChooser.APPROVE_OPTION){	//假如用户选择了保存
                File file = chooser.getSelectedFile();
                String fname = chooser.getName(file);	//从文件名输入框中获取文件名
                if(fname.indexOf(".gizmo")==-1){
                    file = new File(chooser.getCurrentDirectory(),fname+".gizmo");
                    System.out.println("renamed");
                    System.out.println(file.getName());
                }
                GameFrame gameFrame = (GameFrame) getRootPane().getParent();
                gameFrame.saveGamePane(file);
                System.out.println("保存成功");
            }
        }

        public void loadGame(){
            JFileChooser chooser = new JFileChooser(); // 设置选择器
            chooser.setMultiSelectionEnabled(false); // 设为多选
            GameFrame gameFrame = (GameFrame) getRootPane().getParent();
            int returnVal = chooser.showOpenDialog(gameFrame); // 是否打开文件选择框
            System.out.println("returnVal=" + returnVal);
            if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
                File file = chooser.getSelectedFile();
                gameFrame.loadGamePane(file);
            }
        }
    }
}
