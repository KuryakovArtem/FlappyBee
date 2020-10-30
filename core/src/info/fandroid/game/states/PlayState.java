package info.fandroid.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import info.fandroid.game.FlappyBee;
import info.fandroid.game.sprites.Bee;
import info.fandroid.game.sprites.Tree;



public class PlayState extends State{
    public static final int TREE_SPACING = 150;
    public static final int TREE_COUNT =2;
    private static final int GROUND_LEVEL = -30;

    private Bee bee;
    private Texture bg;
    private Texture grass;
    private Vector2 grassPos1, grassPos2;




    private Array<Tree> trees;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bee = new Bee(50,300);
        camera.setToOrtho(false, FlappyBee.WIDTH / 2, FlappyBee.HEIGHT / 2);
        bg = new Texture("bg.png");
        grass = new Texture("grass.png");
        grassPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_LEVEL);
        grassPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + grass.getWidth(), GROUND_LEVEL);



        trees = new Array<Tree>();

        for (int i=0; i < TREE_COUNT; i++);
        trees.add(new Tree(TREE_SPACING + Tree.TREE_WIDHT));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bee.jump();

    }
    @Override
    public void update(float dt) {
        handleInput();
        updateGrass();

        bee.update(dt);
        camera.position.x = bee.getPosition().x + 80;
        for (int i = 0; i < trees.size;i++ ){

            Tree tree = trees.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tree.getPosTopTree().x
                    + tree.getTopTree().getWidth()){
                tree.reposition(tree.getPosTopTree().x + (Tree.TREE_WIDHT + TREE_SPACING * TREE_COUNT));
            }

            if (tree.collites(bee.getBounds()))
                gsm.set(new GameOver(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2),0 );
        sb.draw(bee.getBee(), bee.getPosition().x, bee.getPosition().y);
        for (Tree tree : trees) {
            sb.draw(tree.getTopTree(), tree.getPosBottomTree().x, tree.getPosTopTree().y);
            sb.draw(tree.getBottomTree(), tree.getPosBottomTree().x, tree.getPosBottomTree().y);

        }
        sb.draw(grass, grassPos1.x, grassPos1.y);
        sb.draw(grass, grassPos2.x, grassPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bee.dispose();
        grass.dispose();
        for (Tree tree : trees)
            tree.dispose();


    }
    private void updateGrass(){
        if (camera.position.x- (camera.viewportWidth / 2) > grassPos1.x + grass.getWidth())
            grassPos1.add(grass.getWidth() * 2, 0);
        if (camera.position.x- (camera.viewportWidth / 2) > grassPos2.x + grass.getWidth())
            grassPos2.add(grass.getWidth() * 2, 0);
    }

}
