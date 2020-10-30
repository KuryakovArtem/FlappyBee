package info.fandroid.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;


public class Tree {
    public static final int TREE_WIDHT = 60;

    public static final int FLUCTUATION = 100;
    public static final int TREE_GAP = 150;
    public static final int LOWEST_OPENUNG = 100;// нижняя граница

    private Texture topTree, bottomTree;
    private Vector2 posTopTree, posBottomTree;
    private Random rand;
    private Rectangle boundsTop, boundsBot;


    public Texture getTopTree() {
        return topTree;
    }

    public Texture getBottomTree() {
        return bottomTree;
    }

    public Vector2 getPosTopTree() {
        return posTopTree;
    }

    public Vector2 getPosBottomTree() {
        return posBottomTree;
    }

    public Tree(float x){
        topTree = new Texture("tree2.png");
        bottomTree = new Texture("tree1.png");
        rand = new Random();

        posTopTree = new Vector2(x, rand.nextInt(FLUCTUATION) +TREE_GAP + LOWEST_OPENUNG );
        posBottomTree = new Vector2(x, posTopTree.y - TREE_GAP - bottomTree.getHeight());

        boundsTop = new Rectangle(posTopTree.x, posTopTree.y, topTree.getWidth()/3, topTree.getHeight()/3);
        boundsBot = new Rectangle(posBottomTree.x, posBottomTree.y, bottomTree.getWidth()/3, bottomTree.getHeight()/3);
    }
    public void reposition (float x){
        posTopTree.set(x, rand.nextInt(FLUCTUATION) +TREE_GAP + LOWEST_OPENUNG);
        posBottomTree.set((x), posTopTree.y - TREE_GAP - bottomTree.getHeight());
        boundsTop.setPosition(posTopTree.x, posTopTree.y);
        boundsBot.setPosition(posBottomTree.x, posBottomTree.y);
    }

    public boolean collites (Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
    public void dispose(){
        topTree.dispose();
        bottomTree.dispose();
    }
}
