package listener;

import item.Item;
import panel.GamePane;
import util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.util.Properties;


public class MyKeyListener implements MouseListener {
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
                    Class<Item> onClass = null;
                    kv.load(this.getClass().getClassLoader().getResourceAsStream("properties/item.properties"));

                    onClass = (Class<Item>) Class.forName("item."+ itemType);
                    Constructor<Item> constructor = onClass.getDeclaredConstructor(Integer.class, Integer.class, Image.class);

                    Item item = constructor.newInstance(x, y, (kv.getIcon(kv.getProperty(itemType))).getImage());
                    panel.add(item);
                    panel.repaint();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if(itemType.equals("Click")){
                System.out.println("Click");
                //如果button值为click就判断当前点击的位置上是否有component
                Item item = (Item)panel.getComponentAt(x, y);
                if(item!=null)
                    panel.setCurItem(item);
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
