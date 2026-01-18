package br.com.ajf.libgdx.platformerninja.efects;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PlayerAppearing extends BaseActor
{
    public PlayerAppearing(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadAnimationFromSheet("assets/effects/Appearing.png",1,7,0.2f,false);
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
