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
    private int width;
    private int height;

    //Constructor
    public BlackHole (Integer x, Integer y, Image image){
        super(x, y, image);
        this.radius = (float) Constant.BASE_RADIUS;
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        super.initInWorld();
        //定义刚体
        BodyDef hole = new BodyDef();
        hole.position = new Vec2(x,y);
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
        g.drawImage(image, x, y, width,height,null);
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
