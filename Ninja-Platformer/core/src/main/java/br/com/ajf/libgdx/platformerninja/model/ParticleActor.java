package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class ParticleActor extends Group
{
	private ParticleEffect effect;
	private ParticleRenderer renderingActor;

	public ParticleActor(String filePath,String imageDirectory)
	{
		super();
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal(filePath),Gdx.files.internal(imageDirectory));
		renderingActor = new ParticleRenderer(effect);
		this.addActor(renderingActor);
	}

	public void start()
	{
		effect.start();
	}
	public void stop()
	{
		effect.allowCompletion();
	}

	public boolean isRunning()
	{
		return effect.isComplete();
	}

	public void centerAtActor(Actor other)
	{
		setPosition(other.getX()+other.getWidth()/2,other.getY()+other.getHeight()/2);
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		effect.update(delta);

		if (effect.isComplete() && !effect.getEmitters().first().isContinuous())
		{
			effect.dispose();
			this.remove();
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
	}

	private class ParticleRenderer extends Actor
	{
		private ParticleEffect effect;

		public ParticleRenderer(ParticleEffect effect)
		{
			this.effect = effect;
		}

		@Override
		public void draw(Batch batch, float parentAlpha)
		{
			this.effect.draw(batch);
		}
	}
}
