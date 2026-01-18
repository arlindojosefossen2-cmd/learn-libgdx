package br.com.ajf.libgdx.platformerninja.efects;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PlayerDesappearing extends BaseActor
{
    public PlayerDesappearing(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadAnimationFromSheet("assets/effects/Disappearing.png",1,7,0.2f,false);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (isAnimationFinished())
        {
            remove();
        }
    }
}
