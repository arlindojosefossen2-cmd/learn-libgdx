package br.com.ajf.libgdx.platformerninja.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileMapActor extends Actor
{
	private final TiledMap tiledMap;
	private final OrthographicCamera camera;
	private final OrthoCachedTiledMapRenderer renderer;

	public TileMapActor(String fileName, Stage stage)
	{
		tiledMap = new TmxMapLoader().load(fileName);

		int tw = (int)tiledMap.getProperties().get("tilewidth");
		int th = (int)tiledMap.getProperties().get("tileheight");
		int nth = (int)tiledMap.getProperties().get("width");
		int ntv = (int)tiledMap.getProperties().get("height");
		int mw = tw * nth;
		int mh = th*ntv;

		BaseActor.setWorldBounds(mw,mh);

		renderer = new OrthoCachedTiledMapRenderer(tiledMap);
		renderer.setBlending(true);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.update();

		stage.addActor(this);
	}

	public List<MapObject> getRectangleList(String propertyName)
	{
		List<MapObject> list = new ArrayList<>();

		for (MapLayer layer : tiledMap.getLayers())
		{
			for (MapObject obj : layer.getObjects())
			{
				if (!(obj instanceof RectangleMapObject))
				{
					continue;
				}

				MapProperties props = obj.getProperties();

				if (props.containsKey("name") && props.get("name").equals(propertyName))
				{
					list.add(obj);
				}
			}
		}

		return list;
	}

	public List<MapObject> getTileList(String propertyName)
	{
		List<MapObject> list = new ArrayList<>();

		for (MapLayer layer: tiledMap.getLayers())
		{
			for (MapObject obj : layer.getObjects())
			{
				if (!(obj instanceof TiledMapTileMapObject))
				{
					continue;
				}

				MapProperties props = obj.getProperties();

				TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
				TiledMapTile t = tmtmo.getTile();
				MapProperties defaultProps = t.getProperties();
				if (defaultProps.containsKey("name") && defaultProps.get("name").equals(propertyName))
				{
					list.add(obj);
				}

				Iterator<String> propertyKey = defaultProps.getKeys();

				while(propertyKey.hasNext())
				{
					String key = propertyKey.next();

					if (props.containsKey(key))
					{
						continue;
					}
					else
					{
						Object value = defaultProps.get(key);
						props.put(key,value);
					}
				}
			}
		}

		return list;
	}


		@Override
	public void draw(Batch batch, float parentAlpha)
	{
		Camera mc = getStage().getCamera();
		camera.position.x = mc.position.x;
		camera.position.y = mc.position.y;
		camera.update();
		renderer.setView(camera);

		batch.end();

		renderer.render();

		batch.begin();
	}

    @Override
	public void act(float delta)
	{
		super.act(delta);
	}

	public void dispose()
	{
		renderer.dispose();
		tiledMap.dispose();
	}
}


















