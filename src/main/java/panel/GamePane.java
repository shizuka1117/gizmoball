package panel;

import item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import item.*;

public class GamePane extends JPanel implements Runnable{
    String nextItemName = "";
    Item curItem;
    public GamePane(){
        setPreferredSize(new Dimension(500, 500));

        setVisible(true);
        //标示边界
        Common.updateBounds(500,500);
    }

    @Override
    public Component add(Component component){
        curItem = (Item)component;
        return super.add(component);
    }

    public String getNextItemName() {
        return nextItemName;
    }

    public void setNextItemName(String nextItemName) {
        this.nextItemName = nextItemName;
    }

    public Item getCurItem() {
        return curItem;
    }

    public void setCurItem(Item curItem) {
        System.out.println(curItem);
        this.curItem = curItem;
    }

    @Override
    public void paint(Graphics g) {
        //强制类型转换得到Graphics子类Graphics2D对象
        Graphics2D g2 = (Graphics2D)g;//又得到一支笔
        //绘制格子
        g2.setColor(Color.black);
        g2.fill3DRect(0, 0, 500, 500, true);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        for(int i = 1;i < 20;i ++) {
            g2.drawLine(0,25*i,500,25*i );
        }
        for(int i = 1;i < 20;i ++) {
            g2.drawLine(25*i,0,25*i,500);
        }
        for(Component i: getComponents()){
            i.paint(g);
        }
    }

    @Override
    public void run() {
        try{
            while (true){
                Thread.sleep(5);
                this.repaint();
                logic();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logic() {
        Common.step();
    }
}
