package util;

import item.BlackHole;
import item.Item;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import panel.GamePane;

import java.awt.*;

/**
 * 控制World类（物理引擎）
 */
public class Common {
    private GamePane gamePane;
    public static World world = new World(new Vec2(0f,10f));
    public static final int WIDTH = 500;
    public static final int HIGHT = 500;
    public static final float TIME_STEP = 1f/30f;



    public Common(GamePane gamePane) {
        this.gamePane = gamePane;
        //设置碰撞监听，用于黑洞吸收
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {//检测到碰撞即调用该函数
                //获取碰撞双方的Fixture和组件
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Vec2 position1 = fixtureA.getBody().getPosition();
                Vec2 position2 = fixtureB.getBody().getPosition();
                Component component1 = gamePane.getComponentAt((int)position1.x, (int)position1.y);
                Component component2 = gamePane.getComponentAt((int)position2.x, (int)position2.y);
                //判断碰撞双方中是否有黑洞（碰撞另一方必为小球）
                if(component1 instanceof BlackHole || component2 instanceof BlackHole){
                    gamePane.stop();
                    System.out.println("stp");
                    //获取小球的Fixture
                    Fixture ballFixture = fixtureA.getBody().getType()==BodyType.DYNAMIC?fixtureA:fixtureB;
                    //遍历gamePane的组件，通过判断组件的物体实体（Body）和发生碰撞的小球物理实体是否相同，找到小球组件将其删除
                    for(Component component: gamePane.getComponents()){
                        Item item = (Item) component;
                        if(item.getBody().hashCode()==ballFixture.getBody().hashCode()){
                            ballFixture.setSensor(true);
                            item.destroyInWorld();
                            gamePane.remove(component);
                        }
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gamePane.begin();

                }
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
    }


    /**
     * 由于世界是没有边界的，我们又要在边界有碰撞效果，所以使用刚体设置边界
     */
    public static void updateBounds(int height, int width){
        //创建静态刚体
        BodyDef bodyDef = new BodyDef();

        //左侧和右侧的
        //定义的形状，PolygonShape为多边形，所以可表示矩形
        PolygonShape shape = new PolygonShape();
        //确定为矩形,左侧和右侧刚体的高度为最大边界高度，宽度为1
        shape.setAsBox(1, height);

        //描述
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

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

