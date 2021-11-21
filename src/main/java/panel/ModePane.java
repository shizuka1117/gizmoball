package panel;

import item.Ball;
import item.Item;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ModePane类，用于切换模式
 */
public class ModePane extends JPanel {
    private final JButton button1 = new JButton("布局模式");;
    private final JButton button2 = new JButton("游玩模式");
    public ModePane() {
        setBackground(Color.white);
        Border titleBorder = BorderFactory.createTitledBorder("模式栏");
        setBorder(titleBorder);
        setPreferredSize(new Dimension(100, 100));
        button1.setMargin(new Insets(0,0,0,0));
        button1.setPreferredSize(new Dimension(80, 20));

        button2.setPreferredSize(new Dimension(80, 20));
        button2.setMargin(new Insets(0,0,0,0));

        ActionListener actionListener = new ModeActionListener();
        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);

        add(button1);
        add(button2);
    }
    /**
     * ModePane对应的Listener，用于切换游戏模式
     */
     private class ModeActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            GameFrame gameFrame = (GameFrame) getRootPane().getParent();
            GamePane gamePane = gameFrame.getGamePane();
            //布局模式
            if(e.getSource()==button1) {
                //停止GamePane线程
                button2.setEnabled(true);
                button1.setEnabled(false);
                gamePane.stop();
                //删除刚体
                gamePane.destroyAllBody();
                //恢复添加item的监听器
                gamePane.addMouseListener(gamePane.getMyMouseListener());
            }
            //游戏模式
            else if(e.getSource()==button2){
                button1.setEnabled(true);
                button2.setEnabled(false);
                //设置gamePane线程的循环标志位
                gamePane.begin();
                gamePane.initAllBody();
                //设置gamePane能够获取键盘输入
                gamePane.requestFocus();
                //移除gamePane的鼠标监听（防止在游玩过程中添加item）
                gamePane.removeMouseListener(gamePane.getMyMouseListener());
                new Thread(gamePane).start();
            }
        }
    }
}
