package br.com.ajf.libgdx.platformerninja.model;

public class TypeWriterAction extends SetTextAction
{
	private float elapsedTime;
	private final float characterPerSecond;

	public TypeWriterAction(String textToDisplay)
	{
		super(textToDisplay);
		elapsedTime = 0;
		characterPerSecond = 30;
	}

	@Override
	public boolean act(float dt)
	{
		elapsedTime += dt;
		int nc = (int)(elapsedTime*characterPerSecond);

		if (nc > textToDisplay.length())
		{
			nc = textToDisplay.length();
		}

		String pt = textToDisplay.substring(0,nc);
		DialogBox db = (DialogBox) target;
		db.setText(pt);

		return (nc >= textToDisplay.length());
	}
}
