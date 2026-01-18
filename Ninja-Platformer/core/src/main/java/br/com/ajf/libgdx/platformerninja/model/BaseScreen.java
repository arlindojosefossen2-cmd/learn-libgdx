package br.com.ajf.libgdx.platformerninja.model;

import br.com.ajf.libgdx.platformerninja.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen implements Screen , InputProcessor
{
	protected Stage mainStage;
	protected Stage uiStage;

	protected TileMapActor mapActor;

	protected void initialize(String tileMapName)
    {
        mainStage = new Stage(new FitViewport(1024,576));
        uiStage = new Stage(new FitViewport(1024,576));
    }
    protected void initialize(String tileMapName, Player player)
    {
        mainStage = new Stage(new FitViewport(1024,576));
        uiStage = new Stage(new FitViewport(1024,576));
    }

	protected void update(float dt)
    {

    }

	@Override
	public void show()
	{
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.addProcessor(this);
		im.addProcessor(uiStage);
		im.addProcessor(mainStage);
	}

	@Override
	public void render(float dt)
	{
		uiStage.act(dt);
		mainStage.act(dt);

		update(dt);

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mainStage.draw();
		uiStage.draw();

	}

	@Override
	public void resize(int i, int i1)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.removeProcessor(this);
		im.removeProcessor(uiStage);
		im.removeProcessor(mainStage);
	}

	@Override
	public void dispose()
	{
        mainStage.dispose();
        uiStage.dispose();
        mapActor.dispose();
    }

	@Override
	public boolean keyDown(int keyCode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keyCode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char keyChar)
	{
		return false;
	}

	@Override
	public boolean touchDown(int i, int i1, int i2, int i3)
	{
		return false;
	}

	@Override
	public boolean touchUp(int i, int i1, int i2, int i3)
	{
		return false;
	}

	@Override
	public boolean touchCancelled(int i, int i1, int i2, int i3)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i1, int i2)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int i, int i1)
	{
		return false;
	}

	@Override
	public boolean scrolled(float v, float v1)
	{
		return false;
	}

	protected boolean isTouchDown(Event e)
	{
		return (!(e instanceof InputEvent) || !((InputEvent)e).getType().equals(InputEvent.Type.touchDown));
	}
}
