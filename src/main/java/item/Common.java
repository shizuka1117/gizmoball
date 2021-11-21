package item;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.contacts.Position;
import panel.GamePane;
import util.IconUtil;

import java.awt.*;
import java.util.List;

public class Common {
    private GamePane gamePane;
    public static World world = new World(new Vec2(0f,10f));
    public static final int WIDTH = 500;
    public static final int HIGHT = 500;
//    public static final float RATE = 25f;
    public static final float TIME_STEP = 1f/30f;

//    public static int mile2Pixel(float mile){
//        return (int)(mile*RATE);
//    }
//
//    public static float pixel2Mile(int pixel){
//        return pixel/RATE;
//    }
//
//    public static int toPixelHeight(float mile){
//        return HIGHT - (int) (mile*RATE);
//    }
//
//    public static float pixel2Height(int height){
//        return (HIGHT - height)/RATE;
//    }


    public Common(GamePane gamePane) {
        this.gamePane = gamePane;
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Vec2 position1 = contact.getFixtureA().getBody().getPosition();
                Vec2 position2 = contact.getFixtureB().getBody().getPosition();
                Component component1 = gamePane.getComponentAt((int)position1.x, (int)position1.y);
                Component component2 = gamePane.getComponentAt((int)position2.x, (int)position2.y);
                //为什么第一次碰撞能检测到component，第二次就检测不到？
//                if(component1 instanceof BlackHole || component2 instanceof BlackHole){
//                    gamePane.stop();
//                    System.out.println("stp");
//                    component1 = gamePane.getComponentAt((int)position1.x, (int)position1.y);
//                    component2 = gamePane.getComponentAt((int)position2.x, (int)position2.y);
//                    if (component1 instanceof Ball){
//                        gamePane.remove(component1);
//                        ((Ball) component1).destroyInWorld();
//                    }
//                    else if(component2 instanceof Ball){
//                        gamePane.remove(component2);
//                        ((Ball) component2).destroyInWorld();
//                    }
//                    gamePane.updateUI();
//                }
                //TODO:
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {
            }
        });
    }

    public static void step(){
        world.step(TIME_STEP,6,6);
//        System.out.println(world.getBodyCount());
    }


    /**
     * 由于世界时没有边界的，我们又要在边界有碰撞效果，所以使用刚体设置边界
     */
    public static void updateBounds(int height, int width){
        //创建静态刚体
        BodyDef bodyDef = new BodyDef();
        //bodyDef.type = BodyType.DYNAMIC;//表示这个刚体是静态的

        //左侧和右侧的
        //定义的形状，PolygonShape为多边形，所以可表示矩形
        PolygonShape shape = new PolygonShape();
        //确定为矩形,左侧和右侧刚体的高度为最大边界高度，宽度为1
        shape.setAsBox(1, height);

        //描述
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
//        fixtureDef.density = mDesity;
//        fixtureDef.friction = mFriction;//摩擦系数
//        fixtureDef.restitution = mRestitution; //补偿系数

        //确定左侧刚体的位置
        bodyDef.position.set(-1, 0);//左侧为-1，0
        //通过世界创建刚体
        Body body = world.createBody(bodyDef);
        //赋予刚体属性
        body.createFixture(fixtureDef);

        //确定右侧刚体的位置
        bodyDef.position.set(width + 1, 0);//右侧为width+1，0
        //通过世界创建刚体并赋予属性
        world.createBody(bodyDef).createFixture(fixtureDef);

        //上侧和下侧的刚体
        //左侧和右侧刚体的高度为1，宽度为最大宽度
        shape.setAsBox(width, 1);
        //重新赋值下形状
        fixtureDef.shape = shape;

        //确定上侧刚体的位置
        bodyDef.position.set(0, -1);//上侧为0，-1
        //通过世界创建刚体并赋予属性
        world.createBody(bodyDef).createFixture(fixtureDef);

        //确定下侧刚体的位置
        bodyDef.position.set(0, height + 1);//下侧为0，height+1
        //通过世界创建刚体并赋予属性
        world.createBody(bodyDef).createFixture(fixtureDef);
    }

}

