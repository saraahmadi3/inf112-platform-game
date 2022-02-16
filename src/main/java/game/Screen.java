package game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Screen implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
     
    //TODO Sprites should probably be kept track of by another class, not here.
    private List<Sprite> sprites;
    private Player player; 
    private Tips tip;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        
        //TODO move all the sprite related to different class
        player = new Player();
        tip = new Tips();
        
        //This list should probably be in a different class, and accessible through something like game.getSprites()
        sprites = new ArrayList<Sprite>();
        sprites.add(player);
        sprites.add(tip);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
    
    //Draws a blank screen, used as a reset between each frame
    private void clearScreen() {
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    @Override
    /*
     * Is called every frame, essentially used as the main game loop.
     * Should if possible ONLY call other methods to avoid cluttering
     */
    public void render() {
        clearScreen();
        moveAll();
        drawAll();
    }
    
    //This method should iterate over all movable sprites and call the move() method for each one.
    private void moveAll() {
    	for (Sprite sprite : sprites) {
    		sprite.move();
    	}
    }
    
    //This method should iterate over all sprites and draw each one, preferably by calling their draw() methods (f.ex. player.draw())
    private void drawAll() {
    	batch.begin();//Must happen before all drawing
        
    	for (Sprite sprite : sprites) {
    		sprite.draw(batch, font);
    	}
        
        batch.end();//Must be called when all drawing is complete
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}