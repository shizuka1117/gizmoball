package item;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Circle extends Item{
    float radius;
    Body circleInWorld;
    private int width;
    private int height;
    AffineTransform at = new AffineTransform();

    //Constructor
    public Circle (Integer x, Integer y, String image) {
        super(x, y, image);
        this.radius = (float) Constant.BASE_RADIUS;
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x,y);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef(); //默认
        //圆形
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        circleInWorld = Common.world.createBody(bd);
        circleInWorld.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setTransform(at);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void enlarge(){
        scale += 1;
        radius = (float)Constant.BASE_RADIUS*scale;
        width = Constant.BASE_WIDTH * scale;
        height = Constant.BASE_HEIGHT * scale;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            radius = (float)Constant.BASE_RADIUS * scale;
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
}
