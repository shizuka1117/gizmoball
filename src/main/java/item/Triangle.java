package item;

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
    Vec2[] m_vertices = new Vec2[3]; //null
    Integer h = Constant.BASE_HEIGHT; //三角形边长
    Body triangleInWorld;
    private int width;
    private int height;
    AffineTransform at = new AffineTransform();


    //Constructor
    public Triangle(Integer x, Integer y, String image){
        super(x,y,image);
        this.width = Constant.BASE_WIDTH;
        this.height = Constant.BASE_HEIGHT;
        //initTriangleWorld();
    }
    //world坐标转为三角形的顶点数组


    @Override
    public void initInWorld() {
        super.initInWorld();
        BodyDef bd = new BodyDef();  // 定义刚体
        bd.position = new Vec2(x,y);
        bd.type = BodyType.STATIC; //固定不动的
        //刚体内部属性
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        /**
         * 初始化多边形时给出的多边形顶点坐标需要按照逆时针顺序进行卷绕，
         * 否则也会影响计算的正确性
         */
        m_vertices[0] = new Vec2(x,y);
        m_vertices[1] = new Vec2(x+h,y+h);
        m_vertices[2] = new Vec2(x,y+h);
        //System.out.println(ps.validate());
        //由左上角的坐标得到顶点数组
        /**
         * ??? 无法与球碰撞
         */
        ps.set(m_vertices, 3); //传入顶点序列中第1个顶点坐标的引用，count表示顶点的数量
        fd.shape = ps;
        triangleInWorld = Common.world.createBody(bd);
        triangleInWorld.createFixture(fd);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
       // g2d.setTransform(at);
        g2d.drawImage(image, x, y, width,height,null);
    }

    @Override
    public void enlarge(){
        scale += 1;
        h = Constant.BASE_HEIGHT * scale;
        width = Constant.BASE_WIDTH * scale;
        height = Constant.BASE_HEIGHT * scale;
    }

    @Override
    public void reduce(){
        if (scale > 1){
            scale -= 1;
            h = Constant.BASE_HEIGHT * scale;
            width = Constant.BASE_WIDTH * scale;
            height = Constant.BASE_HEIGHT * scale;
        }
    }

    @Override
    public void rotation() {
        theta = (theta+90)%360;
        System.out.println(theta);
        at.setToRotation(Math.toRadians(theta),x+width/2,y+height/2);
    }
}
