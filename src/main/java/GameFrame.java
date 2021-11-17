import listener.MyKeyListener;
import panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    /**
     * 静态代码块，初始化加载图片
     */

    public GameFrame(){
        //初始化GameFrame顶层窗口
        setTitle("Gizmo Ball");
        setLayout(new FlowLayout());
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        setSize(750, 500);

        MenuPane menuPane = new MenuPane();
        setJMenuBar(menuPane);

        GamePane gamePane = new GamePane();
        MyKeyListener myKeyListener = new MyKeyListener();
        gamePane.addMouseListener(myKeyListener);
        add(gamePane);

        JPanel rightPane = new JPanel();
        rightPane.setPreferredSize(new Dimension(220, 500));
        rightPane.setLayout(new BoxLayout(rightPane, 1));
        ToolPane toolPane = new ToolPane(gamePane);
        ItemPane itemPane= new ItemPane(gamePane);
        ModePane modePane = new ModePane();
        rightPane.add(itemPane);
        rightPane.add(toolPane);
        rightPane.add(modePane);

        setBackground(Color.blue);
        add(rightPane);
        pack();
        new Thread(gamePane).start();
        setVisible(true);

    }

    public static void main(String[] args) throws InterruptedException {
        GameFrame gameFrame = new GameFrame();
//        while(true){
//            gameFrame.repaint();
//            Thread.sleep(1000);
//        }
    }
}
