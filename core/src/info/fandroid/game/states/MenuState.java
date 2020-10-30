package info.fandroid.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import info.fandroid.game.FlappyBee;


public class MenuState extends State {
    private Texture background;
    private Texture playbtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBee.WIDTH / 2, FlappyBee.HEIGHT / 2);
        background = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
        gsm.set(new PlayState(gsm));
    }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playbtn,camera.position.x - playbtn.getWidth() / 2, camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();

    }
}
