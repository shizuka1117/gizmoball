package item;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;

public class Square extends Item{
    float hw ;
    float hh ; //半宽 & 高
    Body squareInWorld;

    //Constructor
    public Square(Integer x, Integer y, String image){
        super(x,y,image);
        this.mwidth = Constant.BASE_WIDTH;
        this.mheight = Constant.BASE_HEIGHT;
        this.hw = mwidth /2;
        this.hh = mheight /2;
    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x+hw,y+hh);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        //多边形
        PolygonShape ps = new PolygonShape();
        /**
         * 将多边形设置为矩形，hw表示矩形半宽，hh表示矩形的半高
         */
        hw = (float) mwidth /2;
        hh = (float) mheight /2;
        ps.setAsBox(hw,hh);
        fd.shape = ps;
        squareInWorld = Common.world.createBody(bd);
        squareInWorld.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+hw,y+hh);
        g2d.drawImage(image,x,y, mwidth, mheight,null);
    }

    @Override
    public void enlarge(){
        scale += 1;
        mwidth = Constant.BASE_WIDTH * scale;
        mheight = Constant.BASE_HEIGHT * scale;
        hw = (float) mwidth /2;
        hh = (float) mheight /2;
        setSize(mwidth, mheight);
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            mwidth = Constant.BASE_WIDTH * scale;
            mheight = Constant.BASE_HEIGHT * scale;
            hw = (float) mwidth /2;
            hh = (float) mheight /2;
        }
        setSize(mwidth, mheight);
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(squareInWorld);
    }

}
