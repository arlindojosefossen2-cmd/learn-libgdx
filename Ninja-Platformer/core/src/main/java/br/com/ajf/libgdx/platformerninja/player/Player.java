package br.com.ajf.libgdx.platformerninja.player;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import br.com.ajf.libgdx.platformerninja.model.Solid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Player extends BaseActor
{
    public static final int MAX_HEALTH = 6;

    private static final int NINJA_FROG = 0;
    private static final int NINJA_PINK_MAN = 1;
    private static final int NINJA_MASKED = 2;
    private static final int NINJA_VIRTUAL_GUY = 3;

    protected Animation<TextureRegion> idle;
    protected Animation<TextureRegion> walk;
    protected Animation<TextureRegion> jump;
    protected Animation<TextureRegion> fall;

    protected float jumpSpeed;
    protected BaseActor belowSensor;

    protected float walkAcceleration;
    protected float walkDeceleration;
    protected float maxHorizontalSpeed;
    protected float gravity;
    protected float maxVerticalSpeed;
    protected float health = MAX_HEALTH;
    private int fruits;

    public Player(float x, float y)
    {
        super(x, y);
    }

    public int getFruits()
    {
        return fruits;
    }

    public void randomPlayerImg()
    {
        switch (MathUtils.random(0,3))
        {
            case NINJA_FROG:
                idle = loadAnimationFromSheet("assets/ninja/Idle.png",1,11,0.2f,true);
                walk = loadAnimationFromSheet("assets/ninja/Run.png",1,12,0.05f,true);
                jump = loadAnimationFromSheet("assets/ninja/Jump.png",1,1,0.1f,false);
                fall = loadTexture("assets/ninja/Fall.png");

                setAnimation(idle);
                break;
            case NINJA_PINK_MAN:
                idle = loadAnimationFromSheet("assets/pinkman/Idle.png",1,11,0.2f,true);
                walk = loadAnimationFromSheet("assets/pinkman/Run.png",1,12,0.05f,true);
                jump = loadAnimationFromSheet("assets/pinkman/Jump.png",1,1,0.1f,false);
                fall = loadTexture("assets/pinkman/Fall.png");

                setAnimation(idle);
                break;
            case NINJA_MASKED:
                idle = loadAnimationFromSheet("assets/masked/Idle.png",1,11,0.2f,true);
                walk = loadAnimationFromSheet("assets/masked/Run.png",1,12,0.05f,true);
                jump = loadAnimationFromSheet("assets/masked/Jump.png",1,1,0.1f,false);
                fall = loadTexture("assets/masked/Fall.png");

                setAnimation(idle);
                break;
            case NINJA_VIRTUAL_GUY:
                idle = loadAnimationFromSheet("assets/virtualguy/Idle.png",1,11,0.2f,true);
                walk = loadAnimationFromSheet("assets/virtualguy/Run.png",1,12,0.05f,true);
                jump = loadAnimationFromSheet("assets/virtualguy/Jump.png",1,1,0.1f,false);
                fall = loadTexture("assets/virtualguy/Fall.png");

                setAnimation(idle);
                break;

        }
    }

    public void setFruits(int fruits)
    {
        this.fruits = fruits;
    }

    public void addActorToStage(Stage stage)
    {
        stage.addActor(this);
        stage.addActor(belowSensor);
    }

	public boolean belowOverlaps(BaseActor solid)
    {
        return belowSensor.overlaps(solid);
    }

    public boolean isOnSolid()
    {
        for (Solid solid : BaseActor.getList(getStage(),Solid.class))
        {
            if (belowOverlaps(solid) && solid.isEnabled() || overlaps(solid))
            {
                return true;
            }
        }
        return false;
    }

    public float getHealth()
    {
        return health;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public boolean isJumping()
    {
        return (velocityVec.y > 0);
    }

    public boolean isFalling()
    {
        return (velocityVec.y < 0);
    }

    public void jump()
    {
        velocityVec.y = jumpSpeed;
    }

    @Override
    public void act(float delta)
    {
        if (!isVisible())
        {
            return;
        }

        super.act(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            accelerationVec.add(-walkAcceleration,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            accelerationVec.add(walkAcceleration,0);
        }

        accelerationVec.add(0,-gravity);

        velocityVec.add(accelerationVec.x*delta,
            accelerationVec.y*delta);

        if (!Gdx.input.isKeyPressed(Input.Keys.LEFT)
            && !Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            float da = walkDeceleration*delta;
            float walkDirection;

            if (velocityVec.x > 0)
            {
                walkDirection = 1;
            }
            else
            {
                walkDirection = -1;
            }

            float walkSpeed = Math.abs(velocityVec.x);
            walkSpeed -= da;

            if (walkSpeed < 0)
            {
                walkSpeed = 0;
            }

            velocityVec.x = walkSpeed*walkDirection;
        }

        velocityVec.x = MathUtils.clamp(velocityVec.x,
            -maxHorizontalSpeed,maxHorizontalSpeed);
        velocityVec.y = MathUtils.clamp(velocityVec.y,
            -maxVerticalSpeed,maxVerticalSpeed);

        moveBy(velocityVec.x*delta,velocityVec.y*delta);
        accelerationVec.set(0,0);
        belowSensor.setPosition(getX()+4,getY()-4);
        alignCamera(false);
        boundToWorld();

        if (isOnSolid())
        {
            belowSensor.setColor(Color.GREEN);

            if (velocityVec.x == 0)
            {
                setAnimation(idle);
            }
            else
            {
                setAnimation(walk);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            {
                jump();
            }
        }
        else
        {
            belowSensor.setColor(Color.RED);

            if (isJumping())
            {
                setAnimation(jump);
            }

            if(isFalling())
            {
                setAnimation(fall);
            }
        }

        if (velocityVec.x > 0)
        {
            setScaleX(1);
        }
        if(velocityVec.x < 0)
        {
            setScaleX(-1);
        }
    }

    public void dispose()
    {

    }
}
