package br.com.ajf.libgdx.platformerninja.screens;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import br.com.ajf.libgdx.platformerninja.model.BaseGame;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class NinjaCreditsScreen extends NinjaAbstractScreen
{
    public NinjaCreditsScreen(String tileMapName)
    {
        super(tileMapName);
    }

    @Override
    protected void initialize(String tileMapName)
    {
        BaseActor hello = new BaseActor(0,0,mainStage);
        hello.loadTexture("assets/hello/Hello.png");
        hello.setSize(1024,576);
        BaseActor.setWorldBounds(hello);

        TextButton back = new TextButton("Back", BaseGame.textButtonStyle);

        back.setPosition(936,486);

        back.addListener(
            (Event e)->
            {
                if(isTouchDown(e))
                {
                    return false;
                }

                BaseGame.setActiveScreen(new NinjaMenuScreen(NinjaAbstractScreen.MENU));

                return true;
            }
        );

       uiStage.addActor(back);
    }

    @Override
    protected void update(float dt)
    {

    }
}
