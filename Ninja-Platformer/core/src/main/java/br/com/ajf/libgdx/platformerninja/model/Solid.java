package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Solid extends BaseActor
{
    public boolean enabled;

    public Solid(float x, float y,float width,float height, Stage stage)
    {
        super(x, y, stage);
        setSize(width,height);
        setBoundaryRectangle();
        enabled = true;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
