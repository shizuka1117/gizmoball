package item;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import util.Constant;

import java.awt.*;

public class Rail extends Item{
    float hw,hh; // 半高 半宽
    Body rail1;
    Body rail2;

    public Rail(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        //初始化两条线段
        BodyDef bd1 = new BodyDef();  // 定义刚体
        //管道 看作两个非常细的矩形
        hw = (float)width/20;
        hh = (float)height/2;
        bd1.position = new Vec2(x-hw,y+hh);
        bd1.type = BodyType.STATIC; //固定不动的
        FixtureDef fd1 = new FixtureDef();
        PolygonShape ps1 = new PolygonShape();
        ps1.setAsBox(hw, hh);
        fd1.shape = ps1;

        BodyDef bd2 = new BodyDef();
        bd2.position = new Vec2(x+width+hw,y + hh);
        bd2.type =BodyType.STATIC;
        FixtureDef fd2 = new FixtureDef();
        PolygonShape ps2 = new PolygonShape();
        ps2.setAsBox(hw,hh);
        fd2.shape = ps2;

        rail1 = Common.world.createBody(bd1);
        rail1.createFixture(fd1);

        rail2 = Common.world.createBody(bd2);
        rail2.createFixture(fd2);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x + width/2,y + height/2);
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
