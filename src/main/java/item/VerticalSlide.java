package item;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class VerticalSlide extends Item{
    private int width;
    private int height;
    float hw ; //半宽
    float hh ; //半高
    Body verticalSlide;

    //constructor
    public VerticalSlide(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = Constant.BASE_WIDTH;
        this.height = 3*Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        hw = (float) width/2;
        hh = (float) height/2;
        bd.position = new Vec2(x + hw, y + hh);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        //多边形
        PolygonShape ps = new PolygonShape();
        /**
         * 将多边形设置为矩形，hw表示矩形半宽，hh表示矩形的半高
         */
        ps.setAsBox(hw/5,hh);
        fd.shape = ps;
        verticalSlide = Common.world.createBody(bd);
        verticalSlide.createFixture(fd);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+hw,y+hh);
        g2d.drawImage(image, x, y, width,height,null);
        g2d.drawImage(image, x, y, width,height,null);
    }

    //挡板不能放大缩小
    @Override
    public void enlarge(){
//        scale += 1;
//        width = Constant.BASE_WIDTH * scale;
//        height = (3 * Constant.BASE_HEIGHT) * scale;
//        hw = width/2;
//        hh = height/2;
    }

    @Override
    public void reduce(){
//        if (scale > 1){
//            scale -= 1;
//            width = Constant.BASE_WIDTH * scale;
//            height = (3 * Constant.BASE_HEIGHT) * scale;
//            hw = width/2;
//            hh = height/2;
//        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    //移动挡板？
    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(verticalSlide);
    }
}
