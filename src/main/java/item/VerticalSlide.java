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
    float hw ; //半宽
    float hh ; //半高
    public Body horizontalSlide;

    //constructor
    public VerticalSlide(Integer x, Integer y, String image){
        super(x,y,image);
        //initHorizontalSlide();
        this.width = 2*Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        //super.initInWorld();
        BodyDef bd = new BodyDef();  // 定义刚体
        hw = (float) width/2;
        hh = (float) height/2;
        bd.position = new Vec2(x + hw, y + hh);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(hw,hh/5);
        fd.shape = ps;
        horizontalSlide = Common.world.createBody(bd);
        horizontalSlide.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+hw,y+hh);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void enlarge(){
//        scale += 1;
//        width = (3 * Constant.BASE_WIDTH) * scale;
//        height = Constant.BASE_HEIGHT * scale;
//        hw = width/2;
//        hh = height/2;

    }

    @Override
    public void reduce(){
//        if (scale > 1){
//            scale -= 1;
//            width = (3 * Constant.BASE_WIDTH) * scale;
//            height = Constant.BASE_HEIGHT * scale;
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
        Common.world.destroyBody(horizontalSlide);
    }
}
