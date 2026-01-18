package br.com.ajf.libgdx.platformerninja.spikes;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Spike extends BaseActor
{
    public Spike(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadTexture("assets/traps/Spikes/idle.png");
        setSize(16,8);
        setBoundaryRectangle();
    }
}
