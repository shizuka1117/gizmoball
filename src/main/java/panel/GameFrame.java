package panel;
import item.Item;
import util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * 顶层Frame类，包含各种Pane
 */
public class GameFrame extends JFrame {
    IconUtil kv = new IconUtil();
    private GamePane gamePane;

    // 静态代码块，初始化加载图片
    {
        try {
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public GameFrame(){
        // 初始化GameFrame顶层窗口
        setTitle("Gizmo Ball");
        setLayout(new FlowLayout());
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        setSize(750, 500);

        // 初始化菜单
        MenuPane menuPane = new MenuPane();
        setJMenuBar(menuPane);

        // 初始化游戏面板
        setGamePane(new GamePane());

        // 初始化右侧工具面板
        JPanel rightPane = new JPanel();
        rightPane.setPreferredSize(new Dimension(220, 500));
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
        ToolPane toolPane = new ToolPane();
        ItemPane itemPane= new ItemPane();
        ModePane modePane = new ModePane();
        rightPane.add(itemPane);
        rightPane.add(toolPane);
        rightPane.add(modePane);

        setBackground(Color.blue);
        add(rightPane);
        pack();
        setVisible(true);
    }

    public void setNextItemName(String nextItemName) {
        gamePane.setItemType(nextItemName);
    }

    public GamePane getGamePane() {
        return gamePane;
    }

    /**
     * 重置gamePane
     * @param gamePane
     */
    public void setGamePane(GamePane gamePane) {
        if(this.gamePane!=null){
            for (Component c:this.gamePane.getComponents()) {
                gamePane.remove(c);
            }
            remove(this.gamePane);
        }
        this.gamePane = gamePane;
        add(gamePane, 0);
    }

    /**
     * 把GamePane写入文件
     * @param file
     */
    public void saveGamePane(File file) {
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(gamePane);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException exception) {
            System.err.println("IO异常");
            exception.printStackTrace();
        }
    }

    /**
     * 加载GamePane
     * @param file
     */
    public void loadGamePane(File file) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            setGamePane((GamePane)objectInputStream.readObject());
            // Image不能序列化，因此重新加载GamePane时也要重新加载每个item的Image
            for(int i = 0; i<gamePane.getComponentCount(); i++){
                Item item = ((Item)gamePane.getComponent(i));
                String imageUrl = item.getImageUrl();
                item.setImage(kv.getImageIcon(imageUrl).getImage());
            }
            gamePane.repaint();
            objectInputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
