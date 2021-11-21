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

    // 是否被吸收,是则不再显示
    private boolean isAbsorbed = false;

    public Ball(Integer x, Integer y, String image) {
        super(x, y, image);
        this.radius = (float) Constant.BASE_RADIUS;
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld(){
        //定义刚体
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(x+radius,y+radius);
        bd.type = BodyType.DYNAMIC;
        //定义描述
        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        fd.restitution = 1f;
        fd.friction = 0.5f;
        body = Common.world.createBody(bd);
        body.createFixture(fd);
    }

    public int getX(){
        int X;
        if(body != null){
            X = (int)(body.getPosition().x - radius);
        }
        else {
            X = x; //x y 记录初始位置， X Y 记录实时位置
        }
        return X;
    }

    public int getY(){
        int Y;
        if(body != null){
            Y = (int)(body.getPosition().y - radius);
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
            Graphics2D g2d = (Graphics2D) g.create();
            System.out.println(getX()+" "+getY());
            //g2d.setTransform(at);
            g2d.rotate(Math.toRadians(theta),x+radius,y+radius);
            g2d.drawImage(image,getX(), getY(), width, height,null);
            g2d.dispose();
        }

    }

    @Override
    public void enlarge(){
        scale += 1;
        radius = (float)Constant.BASE_RADIUS*scale;
        width = Constant.BASE_WIDTH * scale;
        height = Constant.BASE_HEIGHT * scale;
        setSize(width, height);
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            radius = (float)Constant.BASE_RADIUS * scale;
            width = Constant.BASE_WIDTH * scale;
            height = Constant.BASE_HEIGHT * scale;
        }
        setSize(width, height);
    }

    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(body);
        body = null;
    }
}
