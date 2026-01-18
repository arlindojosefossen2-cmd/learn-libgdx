package br.com.ajf.libgdx.platformerninja.fruits;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CollectedFruits extends BaseActor
{
    public CollectedFruits(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadAnimationFromSheet("assets/fruits/Collected.png",1,6,0.1f,false);
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
