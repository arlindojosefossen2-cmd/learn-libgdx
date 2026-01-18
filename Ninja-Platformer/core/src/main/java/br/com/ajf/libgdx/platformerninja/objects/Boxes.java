package br.com.ajf.libgdx.platformerninja.objects;

import br.com.ajf.libgdx.platformerninja.model.Solid;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Boxes extends Solid
{
    public static final String BOX_TYPE_1 = "Box1";
    public static final String BOX_TYPE_2 = "Box2";
    public static final String BOX_TYPE_3 = "Box3";

    public int maxHealth;

    public Animation<TextureRegion> idle;
    public Animation<TextureRegion> hit;
    public Animation<TextureRegion> destructible;

    public int health;
    public boolean solid;

    public Boxes(float x, float y, Stage stage)
    {
        super(x, y,28,24,stage);
        loadBoxRandomImg();
    }

    public Boxes(float x, float y, String boxType, Stage stage)
    {
        super(x, y,28,24, stage);
        loadBoxImg(boxType);
    }

    public void loadBoxRandomImg()
    {
        int rand = MathUtils.random(0,2);

        switch (rand)
        {
            case 0:
                loadBoxImg(BOX_TYPE_1);
                break;
            case 1:
                loadBoxImg(BOX_TYPE_2);
                break;
            case 2:
                loadBoxImg(BOX_TYPE_3);
                break;
        }
    }

    public void loadBoxImg(String boxType)
    {
        switch (boxType)
        {
            case BOX_TYPE_1:

                destructible = loadAnimationFromSheet("assets/boxes/Box1/Break.png",1,4,0.2f,false);
                hit = loadAnimationFromSheet("assets/boxes/Box1/Hit.png",1,3,0.1f,false);
                idle = loadTexture("assets/boxes/Box1/Idle.png");

                maxHealth = 1;
                health = maxHealth;

                setAnimation(idle);
                solid = false;

                setBoundaryRectangle();
                break;

            case BOX_TYPE_2:

                destructible = loadAnimationFromSheet("assets/boxes/Box2/Break.png",1,4,0.2f,false);
                hit = loadAnimationFromSheet("assets/boxes/Box2/Hit.png",1,4,0.1f,false);
                idle = loadTexture("assets/boxes/Box2/Idle.png");

                maxHealth = 3;
                health = maxHealth;

                solid = false;
                setAnimation(idle);

                setBoundaryRectangle();
                break;

            case BOX_TYPE_3:

                destructible = loadAnimationFromSheet("assets/boxes/Box3/Break.png",1,4,0.2f,false);
                hit = loadAnimationFromSheet("assets/boxes/Box3/Hit.png",1,2,0.2f,false);
                idle = loadTexture("assets/boxes/Box3/Idle.png");

                maxHealth = 999;
                health = maxHealth;

                solid = true;
                setAnimation(idle);

                setBoundaryRectangle();
                break;
        }
    }
}
