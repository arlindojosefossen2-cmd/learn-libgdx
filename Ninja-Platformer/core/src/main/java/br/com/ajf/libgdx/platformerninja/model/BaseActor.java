package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class BaseActor extends Group
{
	private final Vector2 accelerationVector2 = new Vector2(0,0);

	private Animation<TextureRegion> animation;
	private float elapsedTime;
	private boolean animationPaused;

	public final Vector2 velocityVec;

	protected final Vector2 accelerationVec;
	private float acceleration;

	private float maxSpeed;
	private float deceleration;

	private static final Rectangle worldBounds = new Rectangle();

	private Polygon boundaryPolygon;

	public BaseActor(float x, float y)
	{
		super();

		animation = null;
		elapsedTime = 0;
		animationPaused = false;
		velocityVec = new Vector2(0,0);
		accelerationVec = new Vector2(0,0);
		acceleration = 0;
		maxSpeed = 1000;
		deceleration = 0;

		setPosition(x,y);
	}

    public BaseActor(float x, float y,Stage stage)
	{
		super();

		animation = null;
		elapsedTime = 0;
		animationPaused = false;
		velocityVec = new Vector2(0,0);
		accelerationVec = new Vector2(0,0);
		acceleration = 0;
		maxSpeed = 1000;
		deceleration = 0;

		setPosition(x,y);
        stage.addActor(this);
	}

    public Animation<TextureRegion> getAnimation()
    {
        return animation;
    }

    public boolean isWithinDistance(float distance, BaseActor other)
	{
		Polygon polygon1 = this.getBoundaryPolygon();

		float scaleX = (this.getWidth()+2*distance)/this.getWidth();
		float scaleY = (this.getHeight()+2*distance)/this.getHeight();

		polygon1.setScale(scaleX,scaleY);

		Polygon polygon2 = other.getBoundaryPolygon();

		if (!polygon1.getBoundingRectangle().overlaps(polygon2.getBoundingRectangle()))
		{
			return false;
		}

		return Intersector.overlapConvexPolygons(polygon1,polygon2);
	}

	public void wrapAroundWorld()
	{
		if (getX() + getWidth() < 0)
		{
			setX(worldBounds.width);
		}

		if (getX() > worldBounds.width)
		{
			setX(-getWidth());
		}

		if (getY()+getHeight() < 0)
		{
			setY(worldBounds.height);
		}

		if (getY() > worldBounds.height)
		{
			setY(-getHeight());
		}
	}

	public void alignCamera(boolean alignOnCenterOfScreen)
	{
		Camera camera = this.getStage().getCamera();
//		Viewport viewport = this.getStage().getViewport();

		if (alignOnCenterOfScreen)
		{
			camera.position.set(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),0);
		}
		else
		{
			camera.position.x = MathUtils.clamp(camera.position.x,
					camera.viewportWidth/2,worldBounds.width-camera.viewportWidth/2);
			camera.position.y = MathUtils.clamp(camera.position.y,
					camera.viewportHeight/2,worldBounds.height-camera.viewportHeight/2);
		}

		camera.update();
	}

	public static Rectangle setWorldBounds(float width,float height)
	{
		return worldBounds.setPosition(0,0).setSize(width,height);
	}

	public static void setWorldBounds(BaseActor baseActor)
	{
		setWorldBounds(baseActor.getWidth(),baseActor.getHeight());
	}

	public void boundToWorld()
	{
		if (getX() < 0)
		{
			setX(0);
		}

		if (getX() + getWidth() > worldBounds.width)
		{
			setX(worldBounds.width-getWidth());
		}

		if (getY() < 0)
		{
			setY(0);
		}

		if (getY() + getHeight() > worldBounds.height)
		{
			setY(worldBounds.height-getHeight());
		}
	}

	public static <T extends BaseActor> int count(Stage stage,Class<T> clazz)
	{
		return getList(stage,clazz).size();
	}

	public static <T extends BaseActor> List<T> getList(Stage stage,Class<T> clazz)
	{
		List<T> list = new ArrayList<>();

		for (Actor obj : stage.getActors())
		{
			if (clazz.isAssignableFrom(obj.getClass()))
				list.add(clazz.cast(obj));
		}

		return list;
	}

	public Vector2 preventOverlaps(BaseActor other)
	{
		Polygon boundaryPolygon1 = this.getBoundaryPolygon();
		Polygon boundaryPolygon2 = other.getBoundaryPolygon();

		if (!boundaryPolygon1.getBoundingRectangle().overlaps(
				boundaryPolygon2.getBoundingRectangle()))
		{
			return null;
		}

		Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();

		boolean polygonOverlap = Intersector.overlapConvexPolygons(boundaryPolygon1,boundaryPolygon2,mtv);

		if (!polygonOverlap)
		{
			return null;
		}

		this.moveBy(mtv.normal.x*mtv.depth,mtv.normal.y*mtv.depth);

		return mtv.normal;
	}

	public void setOpacity(float opacity)
	{
		this.getColor().a = opacity;
	}

	public void centerAtActor(BaseActor other)
	{
		centerAtPosition(other.getX()+other.getWidth()/2,
				other.getY()+other.getHeight()/2);
	}

	public void centerAtPosition(float x,float y)
	{
		setPosition(x-getWidth()/2,y-getHeight()/2);
	}

	public void setBoundaryRectangle()
	{
		float w = getWidth();
		float h = getHeight();

		float[] vertices =
				{
					0, 0, w, 0, w, h, 0, h
				};

		boundaryPolygon = new Polygon(vertices);
	}

	public void setBoundaryPolygon(int numSides)
	{
		float w = getWidth();
		float h = getHeight();

		float[] vertices = new float[2*numSides];

		for (int i = 0; i < numSides; i++)
		{
			float angle = i * 6.28f/numSides;

			vertices[2*i] = w/2*MathUtils.cos(angle) + w/2;
			vertices[2*i+1] = h/2*MathUtils.sin(angle)+h/2;
		}

		boundaryPolygon = new Polygon(vertices);
	}

	public Polygon getBoundaryPolygon()
	{
		boundaryPolygon.setPosition(getX(),getY());
		boundaryPolygon.setOrigin(getOriginX(),getOriginY());
		boundaryPolygon.setRotation(getRotation());
		boundaryPolygon.setScale(getScaleX(),getScaleY());
		return boundaryPolygon;
	}

	public boolean overlaps(BaseActor other)
	{
		Polygon boundaryPolygon1 = this.getBoundaryPolygon();
		Polygon boundaryPolygon2 = other.getBoundaryPolygon();

		if (!boundaryPolygon1.getBoundingRectangle().overlaps(
				boundaryPolygon2.getBoundingRectangle()))
		{
			return false;
		}

		return Intersector.overlapConvexPolygons(boundaryPolygon1,boundaryPolygon2);
	}

	public void setMaxSpeed(float maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}

	public void setDeceleration(float deceleration)
	{
		this.deceleration = deceleration;
	}

	public void setAcceleration(float acceleration)
	{
		this.acceleration = acceleration;
	}

	public void accelerationAtAngle(float angle)
	{
		accelerationVector2.set(acceleration,0);
		accelerationVec.add(accelerationVector2.setAngleDeg(angle));
	}

	public void accelerationForward()
	{
		accelerationAtAngle(getRotation());
	}

	public void setSpeed(float speed)
	{
		if (velocityVec.len() == 0)
		{
			velocityVec.set(speed,speed);
		}
		else
		{
			velocityVec.setLength(speed);
		}
	}

	public float getSpeed()
	{
		return velocityVec.len();
	}

	public void setMotionAngle(float angle)
	{
		velocityVec.setAngleDeg(angle);
	}

	public float getMotionAngle()
	{
		return velocityVec.angleDeg();
	}

	public boolean isMoving()
	{
		return (getSpeed() > 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		batch.setColor(getColor());

		if (animation != null && isVisible())
		{
			batch.draw(animation.getKeyFrame(elapsedTime),
					getX(),getY(),getOriginX(),getOriginY(),
					getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
		}

		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		if (!animationPaused)
		{
			elapsedTime += delta;
		}
	}

	public void applyPhysics(float dt)
	{
		velocityVec.add(accelerationVec.x*dt,accelerationVec.y*dt);

		float speed = getSpeed();

		if (accelerationVec.len() == 0)
		{
			speed -= deceleration * dt;
		}

		speed = MathUtils.clamp(speed,0,maxSpeed);

		setSpeed(speed);

		moveBy(velocityVec.x*dt,velocityVec.y*dt);

		accelerationVec.set(0,0);
	}

	public Animation<TextureRegion> loadAnimationFromSheet(String fileName,int rows,int cols, float frameDuration,boolean loop)
	{

		Texture t = new Texture(Gdx.files.internal(fileName),true);
		t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		int fw = t.getWidth()/cols;
		int fh = t.getHeight()/rows;

		TextureRegion[][] temp = TextureRegion.split(t,fw,fh);

		Array<TextureRegion> textureRegionArray = new Array<>();

		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				textureRegionArray.add(temp[r][c]);
			}
		}

		Animation<TextureRegion> anim = new Animation<>(frameDuration,textureRegionArray);

		if (loop)
		{
			anim.setPlayMode(Animation.PlayMode.LOOP);
		}
		else
		{
			anim.setPlayMode(Animation.PlayMode.NORMAL);
		}

		if (animation == null)
		{
			setAnimation(anim);
		}
		return anim;
	}

	public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,float frameDuration,boolean loop)
	{
		Array<TextureRegion> textureRegionArray = new Array<>();

		for (String fileName : fileNames)
		{
			Texture t = new Texture(Gdx.files.internal(fileName));
			t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			textureRegionArray.add(new TextureRegion(t));
		}

		Animation<TextureRegion> anim = new Animation<>(frameDuration,textureRegionArray);

		if (loop)
		{
			anim.setPlayMode(Animation.PlayMode.LOOP);
		}
		else
		{
			anim.setPlayMode(Animation.PlayMode.NORMAL);
		}

		if (animation == null)
		{
			setAnimation(anim);
		}
		return anim;
	}

	public Animation<TextureRegion> loadTexture(String fileName)
	{
		return loadAnimationFromFiles(new String[]{fileName},1,true);
	}

	public boolean isAnimationFinished()
	{
		return animation.isAnimationFinished(elapsedTime);
	}

	public void setAnimationPaused(boolean paused)
	{
		this.animationPaused = paused;
	}

	public void setAnimation(Animation<TextureRegion> animation)
	{
		this.animation = animation;

		TextureRegion tr = this.animation.getKeyFrame(0);
		float w = tr.getRegionWidth();
		float h = tr.getRegionHeight();
		setSize(w,h);
		setOrigin(w/2,h/2);

		if (boundaryPolygon == null)
		{
			setBoundaryRectangle();
		}
	}

	public static Rectangle getWorldBounds()
	{
		return worldBounds;
	}
}
