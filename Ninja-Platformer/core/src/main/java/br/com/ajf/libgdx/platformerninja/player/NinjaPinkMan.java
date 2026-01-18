package br.com.ajf.libgdx.platformerninja.player;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class NinjaPinkMan extends Player
{
    public NinjaPinkMan(float x, float y)
    {
        super(x, y);

        idle = loadAnimationFromSheet("assets/pinkman/Idle.png",1,11,0.2f,true);
        walk = loadAnimationFromSheet("assets/pinkman/Run.png",1,12,0.05f,true);
        jump = loadAnimationFromSheet("assets/pinkman/Jump.png",1,1,0.1f,false);
        fall = loadTexture("assets/pinkman/Fall.png");

        setAnimation(idle);

        jumpSpeed = 320;

        setBoundaryPolygon(6);

        belowSensor = new BaseActor(0,0);
        belowSensor.loadTexture("assets/white.png");
        belowSensor.setSize(this.getWidth()-8,8);
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(true);

        maxHorizontalSpeed = 100;
        walkAcceleration = 200;
        walkDeceleration = 200;
        gravity = 700;
        maxVerticalSpeed = 1000;
    }
}
