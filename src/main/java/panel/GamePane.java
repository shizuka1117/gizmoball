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

public class GamePane extends JPanel {
    Item curItem;
    List<Item> itemList;
    public GamePane(){
        itemList = new LinkedList<>();
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
    }
    public void addItem(Item item){
        itemList.add(item);
    }

    public void setCurItem(Item item){
        curItem=item;
    }

    public Item getCurItem(){
        return curItem;
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
    }
}
