package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class DropTargetActor extends BaseActor
{
	private boolean targetTable;

	public DropTargetActor(float x, float y, Stage stage)
	{
		super(x, y, stage);
		targetTable = true;
	}

	public boolean isTargetTable()
	{
		return targetTable;
	}

	public void setTargetTable(boolean targetTable)
	{
		this.targetTable = targetTable;
	}
}
