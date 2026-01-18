package br.com.ajf.libgdx.platformerninja.backgorund;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Backgounds extends BaseActor
{
    public Backgounds(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadTexture("assets/backgrounds/Green.png");
    }
}
