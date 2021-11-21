package item;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Common;
import util.Constant;

import java.awt.*;

//监听键盘按键
public class LeftSlide extends Item{
    float hw ; //半宽
    float hh ; //半高

    //constructor
    public LeftSlide(Integer x, Integer y, String image){
        super(x,y,image);
        width = 3*Constant.BASE_WIDTH;
        height = Constant.BASE_HEIGHT;
        setSize(width, height);
    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        hw = (float) width /2;
        hh = (float) height /2;
        bd.position = new Vec2(x + hw, y + hh);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(hw,hh/5);
        fd.shape = ps;
        body = Common.world.createBody(bd);
        body.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(theta),x+hw,y+hh);
        g2d.drawImage(image, x, y, width, height,null);
    }

    @Override
    public void enlarge(){

    }

    @Override
    public void reduce(){

    }

    //移动挡板？
    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(body);
    }

    public void move(int length){
        /**
         * 设置刚体位置和姿态角，position表示要设置的位置坐标，angle表示要设置的姿态角弧度
         */
        body.setTransform(new Vec2(body.getPosition().x + length ,
                body.getPosition().y),0);
    }
}
