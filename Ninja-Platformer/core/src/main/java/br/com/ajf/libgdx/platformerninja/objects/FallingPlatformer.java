package br.com.ajf.libgdx.platformerninja.objects;

import br.com.ajf.libgdx.platformerninja.model.Solid;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FallingPlatformer extends Solid
{
    public final Animation<TextureRegion> off;
    public final Animation<TextureRegion> on;

    public FallingPlatformer(float x, float y, Stage stage)
    {
        super(x, y, 32,10,stage);
        off = loadTexture("assets/traps/FallingPlatforms/Off.png");
        setBoundaryRectangle();
        on = loadAnimationFromSheet("assets/traps/FallingPlatforms/On.png",1,4,0.3f,true);
        setAnimation(on);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        applyPhysics(delta);

        if (this.getY() < 0)
        {
            remove();
        }
    }
}
