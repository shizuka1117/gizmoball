package panel;

import item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import item.*;
import util.IconUtil;

public class GamePane extends JPanel implements Runnable{
    public volatile boolean stop = false;//标志位，控制线程执行

    private MyKeyListener myKeyListener = new MyKeyListener();
    String nextItemName;
    Item curItem;
    public GamePane(){
        addMouseListener(myKeyListener);
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

    public MyKeyListener getMyKeyListener() {
        return myKeyListener;
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
            while (!stop){
                Thread.sleep(5);
                this.repaint();
                logic();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void stop(){
        stop = true;
    }

    public void begin(){
        stop = false;
    }
    private void logic() {
        Common.step();
    }

    private class MyKeyListener implements MouseListener {
        IconUtil kv = new IconUtil();
        @Override
        public void mouseClicked(MouseEvent e) {
            //可以获取
            if(MouseEvent.BUTTON1 == e.getButton()){
                GamePane panel = (GamePane) e.getSource();
                //当类型不为箭头时，根据item类型创建并加入
                String itemType = panel.getNextItemName();
                int x = e.getX();
                int y = e.getY();
                if(!itemType.equals("")&&!itemType.equals("Click")){
                    try {
                        if(panel.getComponentAt(x, y)==panel){
                            Class<Item> onClass = null;
                            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));
                            System.out.println("点击位置："+x+" "+y);
                            onClass = (Class<Item>) Class.forName("item."+ itemType);
                            Constructor<Item> constructor = onClass.getDeclaredConstructor(Integer.class, Integer.class, Image.class);

                            Item item = constructor.newInstance(x, y, (kv.getImageIcon(itemType)).getImage());
                            panel.add(item);
                            panel.repaint();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if(itemType.equals("Click")){
                    System.out.println("Click");
                    //如果button值为click就判断当前点击的位置上是否有component
                    Component component = panel.getComponentAt(x, y);
                    if(component!=panel){
                        panel.setCurItem((Item)component);
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
