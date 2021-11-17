package item;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * 小球继承Runnable用于多线程运行
 */
public class Ball extends Item {
//    //记录
//    double worldX;
//    double worldY;
    float radius; //圆的半径
    public Body ballInWorld;
    // 是否被吸收,是则不再显示
    private boolean isAbsorbed = false;

    public Ball(Integer x, Integer y, Image image) {
        super(x, y, image);
        this.radius = 25/2;
        initBallInWorld();
    }

    private void initBallInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(x,y);
        bd.type = BodyType.DYNAMIC;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        fd.restitution = 1f;

        ballInWorld = Common.world.createBody(bd);
        ballInWorld.createFixture(fd);
    }
    public int getX(){
        int X = (int)ballInWorld.getPosition().x;
        return X;
    }

    public int getY(){
        int Y = (int)ballInWorld.getPosition().y;
        return Y;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image,this.getX(), this.getY(),25,25,null);
    }

}
