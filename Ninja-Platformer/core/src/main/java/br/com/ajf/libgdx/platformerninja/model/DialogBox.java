package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

@SuppressWarnings("unused")
public class DialogBox extends BaseActor
{
	private final Label dialogLabel;
	private final float padding = 8;

	public DialogBox(float x, float y, Stage stage)
	{
		super(x, y, stage);

		loadTexture("assets/translucent-dialog-box.png");
		dialogLabel = new Label("  ",BaseGame.droidSansFont);
		dialogLabel.setWrap(true);
		dialogLabel.setAlignment(Align.topLeft);
		dialogLabel.setPosition(padding,-padding);
		setDialogSize(getWidth(),getHeight());
		addActor(dialogLabel);
	}

	public void setDialogSize(float width,float height)
	{
		setSize(width,height);
		dialogLabel.setWidth(getWidth()-2*padding);
		dialogLabel.setHeight(getHeight()-2*padding);
	}

	public void setText(String text)
	{
		dialogLabel.setText(text);
	}
	public void setFontScale(float scale)
	{
		dialogLabel.setFontScale(scale);
	}
	public void setFontColor(Color color)
	{
		dialogLabel.setColor(color);
	}
	public void setBackgroundColor(Color color)
	{
		setColor(color);
	}
	public void alignTopLeft()
	{
		dialogLabel.setAlignment(Align.topLeft);
	}
	public void alignCenter()
	{
		dialogLabel.setAlignment(Align.center);
	}
}
