package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * MenuPane类，用于新建、保存和读取游戏
 */
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

    /**
     * MenuPane对应的Listener，用于处理新建、保存和读取游戏
     */
    private class MenuClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String menuItemName = e.getActionCommand();
            // 处理不同的menu事件
            switch (menuItemName){
                case "new":
                    newGame();
                    break;
                case "save":
                    saveGame();
                    break;
                case "load":
                    loadGame();
                    break;
            }
        }

        private void newGame() {
            GameFrame gameFrame = (GameFrame) getRootPane().getParent();
            GamePane gamePane = gameFrame.getGamePane();
            // 删除之前的所有组件
            for (Component c:gamePane.getComponents())
                gamePane.remove(c);
            // 需要手动立即更新UI，否则删除的组件仍会显示
            gamePane.updateUI();
        }

        public void saveGame(){
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(null);
            // 文件保存逻辑
            if(option==JFileChooser.APPROVE_OPTION){	// 假如用户选择了保存
                File file = chooser.getSelectedFile();
                String fName = chooser.getName(file);	// 从文件名输入框中获取文件名
                if(!fName.contains(".gizmo")){
                    file = new File(chooser.getCurrentDirectory(),fName+".gizmo");
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
            chooser.setMultiSelectionEnabled(false); // 设为单选
            GameFrame gameFrame = (GameFrame) getRootPane().getParent();
            int returnVal = chooser.showOpenDialog(gameFrame); // 判断是否打开文件选择框
            if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
                File file = chooser.getSelectedFile();
                gameFrame.loadGamePane(file);
            }
            else{
                System.out.println("打开了错误的文件类型");
            }
        }
    }
}
