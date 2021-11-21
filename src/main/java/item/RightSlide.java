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

public class RightSlide extends Item{
    float hw ; //半宽
    float hh ; //半高
    Body RightSlide;

    //constructor
    public RightSlide(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = 2*Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
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
        // 将多边形设置为矩形，hw表示矩形半宽，hh表示矩形的半高
        ps.setAsBox(hw/5,hh);
        fd.shape = ps;
        RightSlide = Common.world.createBody(bd);
        RightSlide.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+hw,y+hh);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    //移动挡板？
    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(RightSlide);
    }
}
