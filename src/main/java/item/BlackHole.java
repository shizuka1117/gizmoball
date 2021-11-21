package item;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;

public class BlackHole extends Item {
    float radius;
    Body holeInWorld;

    //Constructor
    public BlackHole (Integer x, Integer y, String image){
        super(x, y, image);
        this.radius = (float) Constant.BASE_RADIUS;
        this.mwidth = Constant.BASE_WIDTH;
        this.mheight = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        //定义刚体
        BodyDef hole = new BodyDef();
        hole.position = new Vec2(x+radius,y+radius);
        hole.type = BodyType.STATIC;
        //定义描述
        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        fd.restitution = 1f;
        holeInWorld = Common.world.createBody(hole);
        holeInWorld.createFixture(fd);
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+radius,y+radius);
        g2d.drawImage(image, x, y, mwidth, mheight,null);
    }

    @Override
    public void enlarge(){
        scale += 1;
        radius = (float)Constant.BASE_RADIUS*scale;
        mwidth = Constant.BASE_WIDTH * scale;
        mheight = Constant.BASE_HEIGHT * scale;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            radius = (float)Constant.BASE_RADIUS * scale;
            mwidth = Constant.BASE_WIDTH * scale;
            mheight = Constant.BASE_HEIGHT * scale;
        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(holeInWorld);
    }
}
