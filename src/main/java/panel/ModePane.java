package panel;

import item.Ball;
import item.Item;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * ModePanel对应的Listener，用于切换游戏模式
     */
     private class ModeActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            button2.setEnabled(true);
            button1.setEnabled(false);
            GameFrame gameFrame = (GameFrame) getRootPane().getParent();
            GamePane gamePane = gameFrame.getGamePane();
            if(e.getSource()==button1) {
                gamePane.stop();
                for(int i = 0; i<gamePane.getComponentCount(); i++) {
                    Item item = (Item) gamePane.getComponent(i);
                    item.destroyInWorld();
                }
                gamePane.addMouseListener(gamePane.getMyMouseListener());
            }
            //开始游戏
            else if(e.getSource()==button2){
                button1.setEnabled(true);
                button2.setEnabled(false);
                for(int i = 0; i<gamePane.getComponentCount(); i++){
                    Item item = (Item)gamePane.getComponent(i);
                    item.initInWorld();
                }
                gamePane.begin();
                gamePane.requestFocus();
                gamePane.removeMouseListener(gamePane.getMyMouseListener());
                System.out.println(gamePane.getMyMouseListener());
                new Thread(gamePane).start();
            }
        }
    }
}
