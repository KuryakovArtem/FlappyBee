package info.fandroid.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bee {
    private static final int MOVEMENT = 100;// скорость
    private static final int GRAVITY = -15;// гравитация
    private Vector3 position;
    private Vector3 velosity;
private Rectangle bounds;
    private Texture bee;

    public Bee(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        bee = new Texture("bee.png");
        bounds = new Rectangle(x,y, bee.getWidth(), bee.getHeight());
    }
    public Vector3 getPosition() {
        return position;
    }

    public Texture getBee() {
        return bee;
    }

    public void update(float dt) {
        if (position.y > 0)
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        position.add(MOVEMENT * dt, velosity.y,0);
        if(position.y <0)
            position.y = 0;

        velosity.scl(1/dt);
        bounds.setPosition(position.x, position.y);

    }

    public void jump() {
        velosity.y = 250;

    }


    public Rectangle getBounds(){
        return bounds;
    }


    public void dispose() {
        bee.dispose();
    }
}
