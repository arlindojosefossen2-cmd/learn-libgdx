package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public abstract class BaseGame extends Game
{
	private static BaseGame game;
	public static final Label.LabelStyle mStyleFont = new Label.LabelStyle();
	public static final Label.LabelStyle font = new Label.LabelStyle();
	public static final Label.LabelStyle droidSansFont = new Label.LabelStyle();
	public static final Label.LabelStyle masterTagFont = new Label.LabelStyle();

	public static TextButton.TextButtonStyle textButtonStyle;

	public BaseGame()
	{
		game = this;
	}

	@Override
	public void create()
	{
		InputMultiplexer im = new InputMultiplexer();
		Gdx.input.setInputProcessor(im);

		FreeTypeFontGenerator droidFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/DroidSans.ttf"));
		FreeTypeFontGenerator masterFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/master-tag-font.ttf"));

		FreeTypeFontGenerator.FreeTypeFontParameter droidFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter masterFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		droidFontParameter.size = 20;
		droidFontParameter.color = Color.OLIVE;
		droidFontParameter.borderWidth = 1;
		droidFontParameter.borderColor = Color.BLACK;
		droidFontParameter.borderStraight = true;
		droidFontParameter.minFilter = Texture.TextureFilter.Linear;
		droidFontParameter.magFilter = Texture.TextureFilter.Linear;

		droidSansFont.font = droidFontGenerator.generateFont(droidFontParameter);

		masterFontParameter.size = 20;
		masterFontParameter.color = Color.WHITE;
		masterFontParameter.borderWidth = 1;
		masterFontParameter.borderColor = Color.DARK_GRAY;
		masterFontParameter.borderStraight = true;
		masterFontParameter.minFilter = Texture.TextureFilter.Linear;
		masterFontParameter.magFilter = Texture.TextureFilter.Linear;

		masterTagFont.font = masterFontGenerator.generateFont(masterFontParameter);

        mStyleFont.font = new BitmapFont(Gdx.files.internal("assets/fonts/m-style-font.fnt"));
        font.font = new BitmapFont(Gdx.files.internal("assets/fonts/font.fnt"));

		textButtonStyle = new TextButton.TextButtonStyle();

		Texture bt = new Texture(Gdx.files.internal("assets/buttons/button.png"));
		NinePatch bn = new NinePatch(bt,16,16,16,16);

		textButtonStyle.up = new NinePatchDrawable(bn);
		textButtonStyle.font = masterFontGenerator.generateFont(masterFontParameter);
		textButtonStyle.fontColor = Color.GRAY;
	}

	public static void setActiveScreen(BaseScreen screen)
	{
		game.setScreen(screen);
	}
}
