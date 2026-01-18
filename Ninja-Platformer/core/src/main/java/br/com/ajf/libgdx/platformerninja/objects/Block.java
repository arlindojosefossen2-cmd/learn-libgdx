package br.com.ajf.libgdx.platformerninja.objects;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Block extends BaseActor
{
    public Block(float x, float y, float width, float height, Stage stage)
    {
        super(x, y, stage);
        setSize(width,height);
        setBoundaryRectangle();
    }
}
