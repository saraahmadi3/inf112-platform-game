package game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLoop implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private GameState game;
  
    @Override
    public void create() {    	
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        
        game = new GameState();
        game.setBatchAndFont(batch, font);
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
    	if (!game.getGameOver()) {
            clearScreen();
            game.update();
            updateAll();
            drawAll();
    	}
    	else {
    		clearScreen();
    		Texture img = new Texture(Gdx.files.internal("src/main/java/game/img/gameOver.png"));
    		Sprite gameOver = new Sprite(img);
    		batch.begin();
    		gameOver.draw(batch);
    		batch.end();
    	}

        
    }
    

	//This method should iterate over all movable sprites and call the move() method for each one.
    private void updateAll() {
    	for (GameObjects sprite : game.getAllSprites()) {
    		sprite.update();
    	}
    }
    
    //This method should iterate over all sprites and draw each one, preferably by calling their draw() methods (f.ex. player.draw())
    private void drawAll() {
    	batch.begin();//Must happen before all drawing
        
    	for (GameObjects sprite : game.getAllSprites()) {
    		sprite.draw();
    	}
        
        batch.end();//Must be called when all drawing is complete
    }
    
    public SpriteBatch getBatch() {
    	return batch;
    }
    
    public BitmapFont getFont() {
    	return font;
    }
    
    public GameState getGame() {
    	return game;
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