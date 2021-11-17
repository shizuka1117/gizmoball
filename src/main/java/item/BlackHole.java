package item;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

public class BlackHole extends Item {
    float r;
    Body holeInWorld;

    //Constructor
    public BlackHole (Integer x, Integer y, Image image){
        super(x, y, image);
        this.r = 25/2;
        initHoleInWorld();
    }

    private void initHoleInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x,y);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef(); //默认
        //圆形
        CircleShape cs = new CircleShape();
        cs.m_radius = r;
        fd.shape = cs;
        holeInWorld = Common.world.createBody(bd);
        holeInWorld.createFixture(fd);
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image, x, y,25,25,null);
    }
}
