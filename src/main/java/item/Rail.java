package item;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import util.Constant;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Rail extends Item{
    private int width;
    private int height;
    int h = Constant.BASE_HEIGHT;
    Body rail1;
    Body rail2;

    public Rail(Integer x, Integer y, String image){
        super(x,y,image);
        //initRailInWorld();
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        //初始化两条线段
        BodyDef bd1 = new BodyDef();  // 定义刚体
        bd1.position = new Vec2(x,y+h/2);
        bd1.type = BodyType.STATIC; //固定不动的
        FixtureDef fd1 = new FixtureDef();
        EdgeShape es1 = new EdgeShape();
        es1.set(new Vec2(x,y), new Vec2(x,y+h));
        fd1.shape =es1;

        BodyDef bd2 = new BodyDef();
        bd2.position = new Vec2(x+h,y + h/2);
        bd2.type =BodyType.STATIC;
        FixtureDef fd2 = new FixtureDef();
        EdgeShape es2 = new EdgeShape();
        es2.set(new Vec2(x+h,y), new Vec2(x+h,y));
        fd2.shape = es2;

        rail1 = Common.world.createBody(bd1);
        rail1.createFixture(fd1);

        rail2 = Common.world.createBody(bd2);
        rail2.createFixture(fd2);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x + h/2,y + h/2);
        g2d.drawImage(image, x, y, width,height,null);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void enlarge(){
        scale += 1;
        width = Constant.BASE_WIDTH * scale;
        height = Constant.BASE_HEIGHT * scale;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            width = Constant.BASE_WIDTH * scale;
            height = Constant.BASE_HEIGHT * scale;
        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(rail1);
    }
}
