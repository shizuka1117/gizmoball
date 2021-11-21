package item;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;
import java.awt.geom.AffineTransform;

//TODO:监听键盘按键
public class LeftSlide extends Item{
    private int width;
    private int height;
    float hw ; //半宽
    float hh ; //半高
    Body LeftSlide;

    //constructor
    public LeftSlide(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = 3*Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;

    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        hw = (float) width/2;
        hh = (float) height/2;
        bd.position = new Vec2(x + hw, y + hh);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(hw,hh/5);
        fd.shape = ps;
        LeftSlide = Common.world.createBody(bd);
        LeftSlide.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+hw,y+hh);
        g2d.drawImage(image, x, y, width,height,null);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void enlarge(){

    }

    @Override
    public void reduce(){

    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    //移动挡板？
    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(LeftSlide);
    }

    public void move(int length){
        /**
         * 设置刚体位置和姿态角，position表示要设置的位置坐标，angle表示要设置的姿态角弧度
         */
        LeftSlide.setTransform(new Vec2(LeftSlide.getPosition().x + length ,
                LeftSlide.getPosition().y),0);
    }
}
