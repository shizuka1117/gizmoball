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
    private int width;
    private int height;
    float hw ;
    float hh ; //半宽 & 高
    Body squareInWorld;

    //Constructor
    public Square(Integer x, Integer y, Image image){
        super(x,y,image);
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x,y);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        //多边形
        PolygonShape ps = new PolygonShape();
        /**
         * 将多边形设置为矩形，hw表示矩形半宽，hh表示矩形的半高
         */
        hw = (float) width/2;
        hh = (float) height/2;
        ps.setAsBox(hw,hh);
        fd.shape = ps;
        squareInWorld = Common.world.createBody(bd);
        squareInWorld.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image,x,y,width,height,null);
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
        // do nothing
    }

}
