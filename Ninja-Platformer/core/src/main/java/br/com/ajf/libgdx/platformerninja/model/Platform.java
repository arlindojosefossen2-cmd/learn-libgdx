package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends Solid
{
    public Platform(float x, float y, Stage stage)
    {
        super(x, y, 48,16, stage);
        loadRandomPlatformImg();
        setBoundaryRectangle();
    }

    private void loadRandomPlatformImg()
    {
        switch (MathUtils.random(0,2))
        {
            case 0:
                loadTexture("assets/platforms/yellowPlatform.png");
                break;
            case 1:
                loadTexture("assets/platforms/brownPlatform.png");
                break;
            case 2:
                loadTexture("assets/platforms/platePlatform.png");
                break;
        }
    }
}
