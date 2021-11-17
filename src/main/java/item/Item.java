package item;

import util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

//TODO:编写继承Item的子类

/**
 * 继承Jcomponent用于画图，并且JComponent已经实现了Serializable可以用于序列化保存
 */
//TODO:修改setX和setY，使其落在格子内部
public class Item extends JComponent {
    int x = 50;//左上角x坐标
    int y = 50;//左上角y坐标
    double scale;//放大倍数（必须>=1）
    double theta;//旋转角度
    Image image;

    //其他公有属性...


    public Item(Integer x, Integer y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
        setVisible(true);
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 在子类中继续重写
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //g.drawImage(image, x, y,25,25,null);
    }
}
