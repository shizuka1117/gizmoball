package item;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.PolygonShape;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import util.Constant;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Triangle extends Item {
    float worldX, worldY; //坐标/2
    int count = 3; //vertex number
    int h = Constant.BASE_HEIGHT ; //三角形边长
    Body triangleInWorld;
    private int width;
    private int height;


    //Constructor
    public Triangle(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
        this.worldX = (float) super.x/2;
        this.worldY = (float) super.y/2;
        System.out.println(worldX+ " " + worldY);
    }
    //world坐标转为三角形的顶点数组


    @Override
    public void initInWorld() {
        //super.initInWorld();
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x/ 2, y / 2 );
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();

        //由左上角的坐标得到顶点数组
        ps.set(new Vec2[] {new Vec2(worldX,worldY),
                new Vec2(worldX+h,worldY+h),
                new Vec2(worldX,worldY+h) }, count); //传入顶点序列中第1个顶点坐标的引用，count表示顶点的数量
        fd.shape = ps;
        fd.density = 0f;
        triangleInWorld = Common.world.createBody(bd);
        triangleInWorld.createFixture(fd);

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
        h = height;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            width = Constant.BASE_WIDTH * scale;
            height = Constant.BASE_HEIGHT * scale;
            h = height;
        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
    }

    @Override
    public void destroyInWorld(){
        Common.world.destroyBody(triangleInWorld);
    }
}
