package br.com.ajf.libgdx.platformerninja.launch;

import br.com.ajf.libgdx.platformerninja.model.BaseGame;
import br.com.ajf.libgdx.platformerninja.screens.NinjaAbstractScreen;
import br.com.ajf.libgdx.platformerninja.screens.NinjaMenuScreen;

public class Launcher extends BaseGame
{
    @Override
    public void create()
    {
        super.create();
        BaseGame.setActiveScreen(new NinjaMenuScreen(NinjaAbstractScreen.MENU));
    }
}
