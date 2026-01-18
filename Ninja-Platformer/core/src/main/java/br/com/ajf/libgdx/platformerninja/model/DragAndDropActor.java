package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class DragAndDropActor extends BaseActor
{
	private DragAndDropActor self;

	private DropTargetActor dropTarget;

	private float grabOffsetX;
	private float grabOffsetY;

	private float startPositionX;
	private float startPositionY;

	private boolean draggable;

	public DragAndDropActor(float x, float y, Stage stage)
	{
		super(x, y, stage);
		self = this;

		draggable = true;

		addListener(
				new InputListener()
				{
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
					{
						if (!self.isDraggable())
							return false;

						self.grabOffsetX = x;
						self.grabOffsetY = y;

						self.startPositionX = self.getX();
						self.startPositionY =self.getY();

						self.toFront();

						self.addAction(Actions.scaleTo(1.1f,1.1f,0.25f));

						self.onDragStart();

						return true;
					}

					@Override
					public void touchDragged(InputEvent event, float x, float y, int pointer)
					{
						float dx = x - self.grabOffsetX;
						float dy = y - self.grabOffsetY;

						self.moveBy(dx,dy);
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button)
					{
						self.setDropTarget(null);

						float closesDistance = Float.MAX_VALUE;


						for (DropTargetActor dta : BaseActor.getList(self.getStage(), DropTargetActor.class))
						{
							if (dta.isTargetTable() && self.overlaps(dta))
							{
								float currentDistance = Vector2.dst(self.getX(),self.getY(),dta.getX(),dta.getY());

								if (currentDistance < closesDistance)
								{
									closesDistance = currentDistance;
								}
							}
						}

						self.addAction(Actions.scaleTo(1.0f,1.0f,0.25f));

						self.onDrop();
					}
				}
		);
	}

	public void onDragStart()
	{

	}

	public void onDrop()
	{

	}

	public void moveToActor(BaseActor other)
	{
		float x = other.getX()+(other.getWidth()-this.getWidth())/2;
		float y = other.getY()+(other.getHeight()-this.getHeight())/2;

		addAction(Actions.moveTo(x,y,0.50f, Interpolation.pow3));
	}

	public void moveToStart()
	{
		addAction(Actions.moveTo(startPositionX,startPositionY,0.50f,Interpolation.pow3));
	}

	public boolean isDraggable()
	{
		return draggable;
	}

	public void setDraggable(boolean draggable)
	{
		this.draggable = draggable;
	}

	public boolean hasDropTarget()
	{
		return (dropTarget != null);
	}

	public DropTargetActor getDropTarget()
	{
		return dropTarget;
	}

	public void setDropTarget(DropTargetActor dropTarget)
	{
		this.dropTarget = dropTarget;
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);
	}
}
