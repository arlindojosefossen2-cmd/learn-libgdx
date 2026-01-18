package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;

public class SetAnimationAction extends Action
{
	protected Animation<TextureRegion> animation;

	public SetAnimationAction(Animation<TextureRegion> animation)
	{
		this.animation = animation;
	}

	@Override
	public boolean act(float v)
	{
		BaseActor ba = (BaseActor) target;
		ba.setAnimation(animation);
		return true;
	}
}
