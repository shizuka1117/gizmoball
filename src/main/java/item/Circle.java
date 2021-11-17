package item;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

public class Circle extends Item{
    float r;
    Body circleInWorld;

    //Constructor
    public Circle (Integer x, Integer y, Image image) {
        super(x, y, image);
        this.r = 25/2;
        initCircleInWorld();
    }

    private void initCircleInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x,y);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef(); //默认
        //圆形
        CircleShape cs = new CircleShape();
        cs.m_radius = r;
        fd.shape = cs;
        circleInWorld = Common.world.createBody(bd);
        circleInWorld.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image,x,y,25,25,null);
    }
}
