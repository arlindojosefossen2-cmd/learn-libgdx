package br.com.ajf.libgdx.platformerninja.screens;

import br.com.ajf.libgdx.platformerninja.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class NinjaLevelScreen extends NinjaAbstractScreen
{
    private final Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/sound/music.mp3"));

    public NinjaLevelScreen(String tileMapName)
    {
        super(tileMapName);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }
    public NinjaLevelScreen(String tileMapName, Player player)
    {
        super(tileMapName,player);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    @Override
    public void hide()
    {
        super.hide();
        backgroundMusic.stop();
        backgroundMusic.dispose();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        backgroundMusic.stop();
        backgroundMusic.dispose();
    }
}
