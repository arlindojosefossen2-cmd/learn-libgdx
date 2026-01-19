package br.com.ajf.libgdx.platformerninja.objects;

import br.com.ajf.libgdx.platformerninja.model.BaseActor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SpringBoard extends BaseActor
{
    public final Animation<TextureRegion> idle;
    public final Animation<TextureRegion> jump;

    public SpringBoard(float x, float y, Stage stage)
    {
        super(x, y, stage);
       jump = loadAnimationFromSheet("assets/traps/Trampoline/Jump.png",1,8,0.05f,false);
       idle = loadTexture("assets/traps/Trampoline/Idle.png");
       setAnimation(idle);
    }
}
