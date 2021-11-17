package item;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;

/**
 * 小球继承Runnable用于多线程运行
 */
public class Ball extends Item {
    float radius;
    int width;
    int height;
    public Body ballInWorld;
    // 是否被吸收,是则不再显示
    private boolean isAbsorbed = false;

    public Ball(Integer x, Integer y, Image image) {
        super(x, y, image);
        this.radius = (float) Constant.BASE_RADIUS;
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld(){
        super.initInWorld();
        //定义刚体
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(x,y);
        bd.type = BodyType.DYNAMIC;
        //定义描述
        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        fd.restitution = 1f;

        ballInWorld = Common.world.createBody(bd);
        ballInWorld.createFixture(fd);
    }

    public int getX(){
        int X;
        if(ballInWorld != null){
            X = (int)ballInWorld.getPosition().x;
        }
        else {
            X = x;
        }
        return X;
    }

    public int getY(){
        int Y;
        if(ballInWorld != null){
            Y = (int)ballInWorld.getPosition().y;
        }
        else {
            Y = y;
        }
        return Y;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(!isAbsorbed){
            g.drawImage(image,this.getX(), this.getY(),width,height,null);
        }

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
        // do nothing
    }
}
