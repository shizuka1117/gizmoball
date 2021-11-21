package item;

import javax.swing.*;
import java.awt.*;

/**
 * 继承JComponent用于画图，由于JComponent已经实现了Serializable，可以用于序列化保存
 */
public abstract class Item extends JComponent {

    int x = 50;//左上角x坐标
    int y = 50;//左上角y坐标
    int width;
    int height;
    int scale;//放大倍数（必须>=1）
    double theta;//旋转角度
    transient Image image;
    String imageUrl;

    public Item(Integer x, Integer y, String imageUrl){
        setX(x);
        setY(y);
        setImageUrl(imageUrl);
        setBounds(this.x, this.y, 25, 25);
        this.scale = 1;
        this.theta = 0;
        setVisible(true);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /**
     * 设置横坐标，使其落在格子内部
     * @param x
     */
    public void setX(int x) {
        this.x = x/25*25;
    }

    /**
     * 设置纵坐标，使其落在格子内部
     * @param y
     */
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

    //提供统一接口，在子类里进行重写
    public abstract void enlarge(); //放大

    public abstract void reduce();  //缩小

    public abstract void rotation(); //旋转

    public abstract void initInWorld();  //在world中创建该item对应的刚体

    public abstract void destroyInWorld(); //在world里删除对应的刚体
}
