package item;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;

import static util.Constant.BASE_HEIGHT;

public class Curve extends Item {
    float worldX, worldY; //坐标/2
    int count = 3; //vertex number
    int h = Constant.BASE_HEIGHT ; //三角形边长
    Body curveInWorld;
    private int width;
    private int height;


    //Constructor
    public Curve(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
        this.worldX = (float) super.x/2;
        this.worldY = (float) super.y/2;
        System.out.println(worldX+ " " + worldY);
    }

    @Override
    public void initInWorld() {
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(worldX,worldY + h/4);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();

        //由左上角的坐标得到顶点数组
        ps.set(new Vec2[] {new Vec2(worldX,worldY+h/4),
                new Vec2(worldX+h/2,worldY+h),
                new Vec2(worldX,worldY+h) }, count); //传入顶点序列中第1个顶点坐标的引用，count表示顶点的数量
        fd.shape = ps;
        fd.density = 0f;
        curveInWorld = Common.world.createBody(bd);
        curveInWorld.createFixture(fd);

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
        height = BASE_HEIGHT * scale;
        h = height;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            width = Constant.BASE_WIDTH * scale;
            height = BASE_HEIGHT * scale;
            h = height;
        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }
    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(curveInWorld);
    }
}
