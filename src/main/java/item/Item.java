package item;

import util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//TODO:编写继承Item的子类
//TODO:运动过程中component的位置和大小也要修改
/**
 * 继承Jcomponent用于画图，并且JComponent已经实现了Serializable可以用于序列化保存
 */
//TODO:修改setX和setY，使其落在格子内部
public abstract class Item extends JComponent {

    int x = 50;//左上角x坐标
    int y = 50;//左上角y坐标
    int scale;//放大倍数（必须>=1）
    double theta;//旋转角度
    transient Image image;
    String imageUrl;
    //其他公有属性...

    public Item(Integer x, Integer y, String imageUrl){
        setX(x);
        setY(y);
        this.imageUrl = imageUrl;
        System.out.println(this.x+" "+this.y);
        setSize(25, 25);
        setBounds(this.x, this.y, 25, 25);
        this.scale = 1;
        this.theta = 0;
        setVisible(true);
    }

    @Override
    public int getX() {
        return x;
    }

    //setX和setY，使其落在格子内部
    public void setX(int x) {
        this.x = x/25*25;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y/25*25;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(int scale) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    /**
//     * 在子类中继续重写
//     * @param g
//     */
//    @Override
//    public void paint(Graphics g){
//        super.paint(g);
//        //g.drawImage(image, x, y,25,25,null);
//    }

    //提供统一接口，在子类里进行重写的方法
    public abstract void enlarge(); //放大

    public abstract void reduce();  //缩小

    public abstract void rotation(); //旋转

    public abstract void initInWorld();  //在world中创建该item对应的刚体

    public void destroyInWorld(){ } //在world里删除对应的刚体

}
