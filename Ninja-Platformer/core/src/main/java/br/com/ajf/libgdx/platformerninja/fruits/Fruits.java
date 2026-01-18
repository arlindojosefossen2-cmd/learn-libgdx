package br.com.ajf.libgdx.platformerninja.fruits;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Fruits extends BaseActor
{
    public static final String APPLE = "Apple";
    public static final String BANANAS = "Bananas";
    public static final String CHERRIES = "Cherries";
    public static final String KIWI = "Kiwi";
    public static final String MELON = "Melon";
    public static final String ORANGE = "Orange";
    public static final String PINEAPPLE = "Pineapple";
    public static final String STRAWBERRY = "Strawberry";

    public Fruits(float x, float y, Stage stage)
    {
        super(x, y, stage);
        loadFruitRandomImg();

        Action pulse = Actions.sequence(
            Actions.scaleTo(1.1f,1.1f,0.5f),
            Actions.scaleTo(0.8f,0.8f,0.5f));

        this.addAction(Actions.forever(pulse));
        setBoundaryPolygon(8);
    }
    public Fruits(float x, float y,String fruitName, Stage stage)
    {
        super(x, y, stage);
        loadFruitImg(fruitName);

        Action pulse = Actions.sequence(
            Actions.scaleTo(1.1f,1.1f,0.5f),
            Actions.scaleTo(0.8f,0.8f,0.5f));

        this.addAction(Actions.forever(pulse));
        setBoundaryPolygon(8);
    }

    public void loadFruitImg(String fruitName)
    {
        switch (fruitName)
        {
            case APPLE:
                loadAnimationFromSheet("assets/fruits/"+APPLE+".png",1,17,0.05f,true);
                break;
            case BANANAS:
                loadAnimationFromSheet("assets/fruits/"+BANANAS+".png",1,17,0.05f,true);
                break;
            case CHERRIES:
                loadAnimationFromSheet("assets/fruits/"+CHERRIES+".png",1,17,0.05f,true);
                break;
            case KIWI:
                loadAnimationFromSheet("assets/fruits/"+KIWI+".png",1,17,0.05f,true);
                break;
            case MELON:
                loadAnimationFromSheet("assets/fruits/"+MELON+".png",1,17,0.05f,true);
                break;
            case ORANGE:
                loadAnimationFromSheet("assets/fruits/"+ORANGE+".png",1,17,0.05f,true);
                break;
            case PINEAPPLE:
                loadAnimationFromSheet("assets/fruits/"+PINEAPPLE+".png",1,17,0.05f,true);
                break;
            case STRAWBERRY:
                loadAnimationFromSheet("assets/fruits/"+STRAWBERRY+".png",1,17,0.05f,true);
                break;
        }
    }
    public void loadFruitRandomImg()
    {
        int randomImgNumber = MathUtils.random(0,7);

        switch (randomImgNumber)
        {
            case 0:
                loadAnimationFromSheet("assets/fruits/"+APPLE+".png",1,17,0.05f,true);
                break;
            case 1:
                loadAnimationFromSheet("assets/fruits/"+BANANAS+".png",1,17,0.05f,true);
                break;
            case 2:
                loadAnimationFromSheet("assets/fruits/"+CHERRIES+".png",1,17,0.05f,true);
                break;
            case 3:
                loadAnimationFromSheet("assets/fruits/"+KIWI+".png",1,17,0.05f,true);
                break;
            case 4:
                loadAnimationFromSheet("assets/fruits/"+MELON+".png",1,17,0.05f,true);
                break;
            case 5:
                loadAnimationFromSheet("assets/fruits/"+ORANGE+".png",1,17,0.05f,true);
                break;
            case 6:
                loadAnimationFromSheet("assets/fruits/"+PINEAPPLE+".png",1,17,0.05f,true);
                break;
            case 7:
                loadAnimationFromSheet("assets/fruits/"+STRAWBERRY+".png",1,17,0.05f,true);
                break;
        }
    }
}
