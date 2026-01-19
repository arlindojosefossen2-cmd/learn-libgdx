package br.com.ajf.libgdx.platformerninja.screens;

import br.com.ajf.libgdx.platformerninja.objects.Block;
import br.com.ajf.libgdx.platformerninja.objects.Boxes;
import br.com.ajf.libgdx.platformerninja.efects.PlayerAppearing;
import br.com.ajf.libgdx.platformerninja.efects.PlayerDesappearing;
import br.com.ajf.libgdx.platformerninja.fruits.CollectedFruits;
import br.com.ajf.libgdx.platformerninja.fruits.Fruits;
import br.com.ajf.libgdx.platformerninja.model.*;
import br.com.ajf.libgdx.platformerninja.objects.SpringBoard;
import br.com.ajf.libgdx.platformerninja.player.*;
import br.com.ajf.libgdx.platformerninja.spikes.Spike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

public class NinjaAbstractScreen extends BaseScreen
{
    private static final String[] LEVELS =
        {
            "level1",
            "level2",
            "level3",
            "level4"
       };

    protected final Player[] PLAYERS =
        {
            new NinjaMaked(0,0),
            new NinjaVirtualGuy(0,0),
            new NinjaFrog(0,0),
            new NinjaPinkMan(0,0),
        };

    public static final String MENU = "menu";

    protected Player player;

    protected PlayerDesappearing disappearing;
    protected PlayerAppearing appearing;

    protected Label title;
    protected Label health;
    protected Label fruitsLabel;

    private List<Fruits> fruitsList;

    private boolean gameOver;

    public NinjaAbstractScreen(String tileMapName)
    {
        initialize(tileMapName);
    }

    public NinjaAbstractScreen(String tileMapName,Player player)
    {
        initialize(tileMapName,player);
    }


    protected void initialize(String tileMapName)
    {
        super.initialize(tileMapName);
        fruitsList = new ArrayList<>();
        mapActor = new TileMapActor("assets/maps/"+tileMapName+".tmx",mainStage);

        for (MapObject obj : mapActor.getRectangleList("Solid"))
        {
            MapProperties props = obj.getProperties();

            new Solid(
                (float) props.get("x"),
                (float) props.get("y"),
                (float) props.get("width"),
                (float) props.get("height"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getRectangleList("Block"))
        {
            MapProperties props = obj.getProperties();

            new Block(
                (float) props.get("x"),
                (float) props.get("y"),
                (float) props.get("width"),
                (float) props.get("height"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getTileList("Platform"))
        {
            MapProperties props = obj.getProperties();
            new Platform(
                (float)  props.get("x"),
                (float)  props.get("y"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getTileList("Spike"))
        {
            MapProperties props = obj.getProperties();

            new Spike(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getTileList("Spring"))
        {
            MapProperties props = obj.getProperties();
            new SpringBoard(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getRectangleList("Fruits"))
        {
            MapProperties props = obj.getProperties();

          fruitsList.add(new Fruits(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            ));
        }

        for (MapObject obj : mapActor.getTileList("Boxes"))
        {
            MapProperties props = obj.getProperties();

            new Boxes(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            );
        }

        if (tileMapName.equals(MENU))
        {
            menuSettings();
        }

        MapObject obj = mapActor.getRectangleList("Start").get(0);
        MapProperties props = obj.getProperties();

        player = randomPlayer();
        player.addActorToStage(mainStage);

        player.setPosition(
            (float) props.get("x"),
            (float) props.get("y"));

        player.setVisible(false);

        health = new Label("Health X "+player.getHealth(),BaseGame.masterTagFont);
        health.setPosition(96,548,Align.center);
        health.setColor(Color.BLACK);
        health.setFontScale(1.1f);
        health.setVisible(true);

        fruitsLabel = new Label("Fruits X "+player.getFruits(),BaseGame.masterTagFont);
        fruitsLabel.setPosition(1024/2f,548,Align.center);
        fruitsLabel.setColor(Color.BLACK);
        fruitsLabel.setFontScale(1.1f);
        fruitsLabel.setVisible(true);

        uiStage.addActor(health);
        uiStage.addActor(fruitsLabel);
        appearing = new PlayerAppearing(0,0,mainStage);
        appearing.centerAtActor(player);
    }

    @Override
    protected void initialize(String tileMapName, Player player)
    {
        super.initialize(tileMapName, player);

        fruitsList = new ArrayList<>();
        mapActor = new TileMapActor("assets/maps/"+tileMapName+".tmx",mainStage);

        for (MapObject obj : mapActor.getRectangleList("Solid"))
        {
            MapProperties props = obj.getProperties();

            new Solid(
                (float) props.get("x"),
                (float) props.get("y"),
                (float) props.get("width"),
                (float) props.get("height"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getRectangleList("Block"))
        {
            MapProperties props = obj.getProperties();

            new Block(
                (float) props.get("x"),
                (float) props.get("y"),
                (float) props.get("width"),
                (float) props.get("height"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getTileList("Platform"))
        {
            MapProperties props = obj.getProperties();
            new Platform(
                (float)  props.get("x"),
                (float)  props.get("y"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getTileList("Spike"))
        {
            MapProperties props = obj.getProperties();

            new Spike(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getTileList("Spring"))
        {
            MapProperties props = obj.getProperties();
            new SpringBoard(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            );
        }

        for (MapObject obj : mapActor.getRectangleList("Fruits"))
        {
            MapProperties props = obj.getProperties();

            fruitsList.add(new Fruits(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            ));
        }

        for (MapObject obj : mapActor.getTileList("Boxes"))
        {
            MapProperties props = obj.getProperties();

            new Boxes(
                (float) props.get("x"),
                (float) props.get("y"),
                mainStage
            );
        }

        if (tileMapName.equals(MENU))
        {
            menuSettings();
        }

        MapObject obj = mapActor.getRectangleList("Start").get(0);
        MapProperties props = obj.getProperties();

        this.player = player;
        this.player.randomPlayerImg();
        this.player.addActorToStage(mainStage);

        if (this.player.isMoving())
        {
            this.player.setSpeed(0);
        }

        this.player.setPosition(
            (float) props.get("x"),
            (float) props.get("y"));

        this.player.setVisible(false);

        health = new Label("Health X "+player.getHealth(),BaseGame.masterTagFont);
        health.setPosition(96,548,Align.center);
        health.setColor(Color.BLACK);
        health.setFontScale(1.1f);
        health.setVisible(true);

        fruitsLabel = new Label("Fruits X "+player.getFruits(),BaseGame.masterTagFont);
        fruitsLabel.setPosition(1024/2f,548,Align.center);
        fruitsLabel.setColor(Color.BLACK);
        fruitsLabel.setFontScale(1.1f);
        fruitsLabel.setVisible(true);

        uiStage.addActor(health);
        uiStage.addActor(fruitsLabel);
        appearing = new PlayerAppearing(0,0,mainStage);
        appearing.centerAtActor(this.player);

    }

    private void menuSettings()
    {
        MapObject obj = mapActor.getRectangleList("Title").get(0);
        MapProperties props = obj.getProperties();

        title = new Label("Ninja Fruits Collector",BaseGame.font);
        title.setPosition((float) props.get("x"),(float) props.get("y"), Align.center);
        title.setFontScale(1.5f);
        title.setColor(Color.BROWN);
        title.setVisible(true);

        uiStage.addActor(title);

        obj = mapActor.getRectangleList("BtnStart").get(0);
        props = obj.getProperties();

        TextButton startBtn = new TextButton("Start",BaseGame.textButtonStyle);

        startBtn.setPosition(
            (float) props.get("x"),
            (float) props.get("y"));

        startBtn.addListener(
            (Event e) ->
            {
                if (isTouchDown(e))
                {
                    return false;
                }

                BaseGame.setActiveScreen(new NinjaLevelScreen(randomLevel()));

                return false;
            }
        );

        uiStage.addActor(startBtn);

        obj = mapActor.getRectangleList("BtnCredits").get(0);
        props = obj.getProperties();

        TextButton creditsBtn = new TextButton("Credits",BaseGame.textButtonStyle);

        creditsBtn.setPosition(
            (float) props.get("x"),
            (float) props.get("y"));

        creditsBtn.addListener(
            (Event e) ->
            {
                if (isTouchDown(e))
                {
                    return false;
                }

                BaseGame.setActiveScreen(new NinjaCreditsScreen("Credits"));

                return false;
            }
        );

        uiStage.addActor(creditsBtn);

        obj = mapActor.getRectangleList("BtnExit").get(0);
        props = obj.getProperties();

        TextButton exitBtn = new TextButton("Exit",BaseGame.textButtonStyle);

        exitBtn.setPosition(
            (float) props.get("x"),
            (float) props.get("y"));

        exitBtn.addListener(
            (Event e) ->
            {
                if (isTouchDown(e))
                {
                    return false;
                }

                Gdx.app.exit();

                return false;
            }
        );

        uiStage.addActor(exitBtn);
    }

    @Override
    public boolean keyDown(int keyCode)
    {
        if (gameOver)
        {
            return false;
        }

        if (keyCode == Input.Keys.SPACE)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            {
                for (Platform platform : BaseActor.getList(mainStage, Platform.class))
                {
                    if (player.belowOverlaps(platform))
                    {
                        platform.setEnabled(false);
                    }
                }
            }
            else
            {
                player.jump();
                player.jumpSound.play(0.5f);
            }
        }

        return false;
    }

    @Override
    protected void update(float dt)
    {
        health.setText("Health X "+player.getHealth());
        fruitsLabel.setText("Fruits X "+player.getFruits());

        if (player.getHealth() <= 0)
        {
            if (disappearing == null)
            {
                gameOver = true;
                disappearing = new PlayerDesappearing(0, 0, mainStage);
                disappearing.centerAtActor(player);
                player.setSpeed(0);
                player.remove();
            }
        }

        if (disappearing != null && disappearing.isAnimationFinished())
        {
            disappearing.setVisible(false);
            player.setVisible(false);
            player.setBelowSensorVisible(false);
            player.setSpeed(0);
            disappearing.remove();

            if (player.getHealth() <= 0 && gameOver)
            {
                player.remove();
                BaseGame.setActiveScreen(new NinjaMenuScreen(MENU));
            }
            else if(fruitsList.isEmpty())
            {
                player.remove();
                BaseGame.setActiveScreen(new NinjaLevelScreen(randomLevel(),player));
            }
        }

        if (appearing.isAnimationFinished() && appearing.isVisible())
        {
            appearing.setVisible(false);
            appearing.remove();
            player.setVisible(true);
            player.setBelowSensorVisible(true);
        }
        else if(!appearing.isAnimationFinished())
        {
            appearing.centerAtActor(player);
        }

        if (player.isVisible())
        {
            for (Platform platform : BaseActor.getList(mainStage, Platform.class))
            {
                if (player.isFalling() && !player.overlaps(platform) && !player.belowOverlaps(platform))
                {
                    platform.setEnabled(true);
                }

                if (player.isJumping() && player.overlaps(platform))
                {
                    platform.setEnabled(false);
                }
                if (player.isJumping() && !player.overlaps(platform))
                {
                    platform.setEnabled(true);
                }
            }

            for (Block block : BaseActor.getList(mainStage, Block.class))
            {
                if (player.overlaps(block))
                {
                    player.preventOverlaps(block);
                    Vector2 hp = new Vector2(player.getX(), player.getY());
                    Vector2 fp = new Vector2(block.getX(), block.getY());
                    player.setMotionAngle(hp.sub(fp).angleDeg());
                    player.setSpeed(10);
                }
            }

            for (SpringBoard spring : BaseActor.getList(mainStage, SpringBoard.class))
            {
                if (player.overlaps(spring))
                {
                    player.spring();
                    spring.setAnimation(spring.jump);
                } else
                {
                    spring.setAnimation(spring.idle);
                }
            }

            for (int i = 0; i < fruitsList.size(); i++)
            {
                Fruits fruits = fruitsList.get(i);

                if (player.overlaps(fruits))
                {
                    CollectedFruits collected = new CollectedFruits(0, 0, mainStage);
                    collected.centerAtActor(fruits);
                    fruits.remove();
                    fruitsList.remove(fruits);
                    player.setFruits(player.getFruits() + 1);
                }
            }

            if (fruitsList.isEmpty())
            {
                if (disappearing == null)
                {
                    disappearing = new PlayerDesappearing(0, 0, mainStage);
                    disappearing.centerAtActor(player);
                    player.setVisible(false);
                    player.setBelowSensorVisible(false);
                    player.setSpeed(0);
                }
            }

            for (Spike spike : BaseActor.getList(mainStage, Spike.class))
            {
                if (player.overlaps(spike))
                {
                    player.preventOverlaps(spike);
                    player.setHit(true);
                    Vector2 hp = new Vector2(player.getX(), player.getY());
                    Vector2 fp = new Vector2(spike.getX(), spike.getY());
                    player.setMotionAngle(hp.sub(fp).angleDeg());
                    player.setSpeed(400);
                    player.setHealth(player.getHealth() - 0.5f);
                }
            }

            for (Boxes box : BaseActor.getList(mainStage, Boxes.class))
            {
                if (player.isJumping() || player.isFalling())
                {
                    if (player.overlaps(box))
                    {
                        if (!box.solid)
                        {
                            player.preventOverlaps(box);
                            box.setAnimation(box.hit);
                            Vector2 hp = new Vector2(player.getX(), player.getY());
                            Vector2 fp = new Vector2(box.getX(), box.getY());
                            player.setMotionAngle(hp.sub(fp).angleDeg());
                            player.setSpeed(200);
                            box.health--;
                        } else
                        {
                            player.preventOverlaps(box);
                        }
                    } else
                    {
                        box.setAnimation(box.idle);
                    }
                }

                if (box.health <= 0)
                {
                    box.setAnimation(box.destructible);

                    if (box.isAnimationFinished())
                    {
                        Fruits f = new Fruits(0, 0, mainStage);
                        fruitsList.add(f);
                        f.centerAtActor(box);
                        box.setVisible(false);
                        box.remove();
                    }
                }
            }

            for (Solid solid : BaseActor.getList(mainStage, Solid.class))
            {
                if (player.overlaps(solid) && solid.isEnabled() && !(solid instanceof Boxes))
                {
                    Vector2 offset = player.preventOverlaps(solid);

                    if (offset != null)
                    {
                        if (Math.abs(offset.x) > Math.abs(offset.y))
                        {
                            player.velocityVec.x = 0;
                        } else
                        {
                            player.velocityVec.y = 0;
                        }
                    }
                }
            }
        }
    }

    public String randomLevel()
    {
        return LEVELS[MathUtils.random(0,LEVELS.length-1)];
    }

    public Player randomPlayer()
    {
        return PLAYERS[MathUtils.random(0,PLAYERS.length-1)];
    }

    @Override
    public void dispose()
    {
        super.dispose();
        player.dispose();
    }
}
