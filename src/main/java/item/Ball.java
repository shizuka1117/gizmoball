package item;

import java.awt.*;

/**
 * 小球继承Runnable用于多线程运行
 */
public class Ball extends Item implements Runnable{


    public Ball(Integer x, Integer y, Image image) {
        super(x, y, image);
    }

    @Override
    public void run() {

    }
}
