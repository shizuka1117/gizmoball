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
    float hw = 5/2; //半宽
    float hh = 25/2; //半高
    Body horizontalSlide;
    AffineTransform at = new AffineTransform();

    //constructor
    public VerticalSlide(Integer x, Integer y, Image image){
        super(x,y,image);
       // initVerticalSlide();
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        super.initInWorld();
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x,y);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        //多边形
        PolygonShape ps = new PolygonShape();
        /**
         * 将多边形设置为矩形，hw表示矩形半宽，hh表示矩形的半高
         */
        ps.setAsBox(hw,hh);
        fd.shape = ps;
        horizontalSlide = Common.world.createBody(bd);
        horizontalSlide.createFixture(fd);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
       // g2d.setTransform(at);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void enlarge(){
        scale += 1;
        hw = (Constant.BASE_WIDTH/2) * scale;
        hh = (Constant.BASE_HEIGHT/2) * scale;
        width = Constant.BASE_WIDTH * scale;
        height = Constant.BASE_HEIGHT * scale;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            hw = (Constant.BASE_WIDTH/2) * scale;
            hh = (Constant.BASE_HEIGHT/2) * scale;
            width = Constant.BASE_WIDTH * scale;
            height = Constant.BASE_HEIGHT * scale;
        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
        System.out.println(theta);
        at.setToRotation(Math.toRadians(theta),x+width/2,y+height/2);
    }
    //移动挡板？
}
