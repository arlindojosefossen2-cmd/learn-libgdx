package br.com.ajf.libgdx.platformerninja.objects;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EndPoint extends BaseActor
{
    public EndPoint(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadAnimationFromSheet("assets/checkpoints/End/EndPressed.png",1,8,0.1f,true);
    }
}
