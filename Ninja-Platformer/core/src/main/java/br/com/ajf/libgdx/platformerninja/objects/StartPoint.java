package br.com.ajf.libgdx.platformerninja.objects;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StartPoint extends BaseActor
{
    public StartPoint(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadAnimationFromSheet("assets/checkpoints/Start/StartMoving.png",1,17,0.05f,true);
    }
}
