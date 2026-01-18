package br.com.ajf.libgdx.platformerninja.screens;

import br.com.ajf.libgdx.platformerninja.player.Player;

public class NinjaLevelScreen extends NinjaAbstractScreen
{
    public NinjaLevelScreen(String tileMapName)
    {
        super(tileMapName);
    }
    public NinjaLevelScreen(String tileMapName, Player player)
    {
        super(tileMapName,player);
    }
}
