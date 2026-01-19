package br.com.ajf.libgdx.platformerninja.player;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;

public class NinjaFrog extends Player
{
    public NinjaFrog(float x, float y)
    {
        super(x, y);

        getPlayerImgById(NINJA_FROG);
        setBoundaryPolygon(8);
        belowSensor = new BaseActor(0,0);
        belowSensor.loadTexture("assets/white.png");
        belowSensor.setSize(this.getWidth(),8);
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(true);

    }
}
