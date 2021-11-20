package panel;

import item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import item.*;
import util.IconUtil;

public class GamePane extends JPanel implements Runnable {
    IconUtil kv = new IconUtil();
    {
        try {
            kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    private transient String itemType;
    private HorizontalSlide hSlide;
    private VerticalSlide vSlide;
    public volatile Boolean stop = false;//标志位，控制线程执行
    private MyMouseListener myMouseListener = new MyMouseListener();
    private MyKeyListener myKeyListener = new MyKeyListener();
    private transient Item curItem;
    public GamePane(){
        addMouseListener(myMouseListener);
        addKeyListener(myKeyListener);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        //标示边界
        Common.updateBounds(500,500);
    }

    public Component add(Item item){
        curItem = item;
        return super.add(item);
    }

    public Item getCurItem() {
        return curItem;
    }

    public void setCurItem(Item curItem) {
        this.curItem = curItem;
    }

    public MyMouseListener newMyMouseListener() {
        return new MyMouseListener();
    }

    public MyMouseListener getMyMouseListener() {
        return myMouseListener;
    }

    public HorizontalSlide getHSlide() {
        return hSlide;
    }
    public void setHSlide(HorizontalSlide hSlide) {
        this.hSlide = hSlide;
    }

    public VerticalSlide getVSlide() {
        return vSlide;
    }

    public void setVSlide(VerticalSlide vSlide) {
        this.vSlide = vSlide;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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


    public class MyMouseListener implements MouseListener, Serializable {

        @Override
        public void mouseClicked(MouseEvent e) {
            //可以获取
            if(MouseEvent.BUTTON1 == e.getButton()){
                GamePane panel = (GamePane) e.getSource();
                //当类型不为箭头时，根据item类型创建并加入
                String itemType = panel.getItemType();
                int x = e.getX();
                int y = e.getY();
                if(!(itemType==null)&&!itemType.equals("Click")){
                    try {
                        if(panel.getComponentAt(x, y)==panel){
                            Class<Item> onClass = (Class<Item>) Class.forName("item."+ itemType);
                            Constructor<Item> constructor = onClass.getDeclaredConstructor(Integer.class, Integer.class, String.class);
                            Item item = constructor.newInstance(x, y, itemType);
                            item.setImage(kv.getImageIcon(itemType).getImage());
                            panel.add(item);
                            if(item instanceof HorizontalSlide)
                                setHSlide((HorizontalSlide)item);
                            if(item instanceof VerticalSlide)
                                setVSlide((VerticalSlide)item);
                            panel.repaint();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if(itemType.equals("Click")){
                    //如果button值为click就判断当前点击的位置上是否有component
                    Component component = panel.getComponentAt(x, y);
                    System.out.println(component);
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

    private class MyKeyListener implements KeyListener, Serializable {
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            GamePane panel = (GamePane)e.getSource();
            HorizontalSlide hSlide = panel.getHSlide();
            VerticalSlide vSlide = panel.getVSlide();
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    hSlide.setX(hSlide.getX()-25);break;
                case KeyEvent.VK_RIGHT:
                    hSlide.setX(hSlide.getX()+25);break;
                case KeyEvent.VK_A:
                    vSlide.setX(vSlide.getX()-25);break;
                case KeyEvent.VK_D:
                    vSlide.setX(vSlide.getX()+25);break;
            }
            System.out.println("pressed");
            //TODO:修改刚体坐标
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
