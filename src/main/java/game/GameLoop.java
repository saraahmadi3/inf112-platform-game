package game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.*;

public class GameLoop implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private GameState game;
	private Music music;
    
    
    private int delayedEnd;
    private boolean isSynced;
    
    @Override
    public void create() { 
    	
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        
        game = new GameState(this);
        game.setBatchAndFont(batch, font);
    
        delayedEnd = 2;
        isSynced = false;
        
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/song.mp3"));
        music.setVolume(1.0f);
        music.setLooping(true);
        music.play();
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        music.dispose(); 
        System.exit(0);
    }
    
    //Draws a blank screen, used as a reset between each frame
    public static void clearScreen() {
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
    


    @Override
    /*
     * Is called every frame, essentially used as the main game loop.
     * Should if possible ONLY call other methods to avoid cluttering.
     * Render uses not only the most recent frame, but also the previous one
     * to make a smooth transition, because of this, and still frames like 
     * the gameover screen must be rendered twice before drawing is stopped.
     * 
     */
    public void render() {
    	if (!game.getGameOver()) {
            clearScreen();
            game.update();
            updateAll();
            drawAll();  
    	} else if (delayedEnd>0){
    		game.gameOver();
    		delayedEnd--;
    	}
    
    	updateMultiPlayer();
    }
    
	
	private void updateMultiPlayer() {
    	if (game.getMultiPlayer()) {
    		PosServer s = game.getServer();
	    	if (s != null) {
	    		if (!isSynced && !s.loggedIn.isEmpty()) {
	    			isSynced = true;
	    			game.level(game.getCurrentLevel());
	    		} else {
	    			s.sendMsg();
		    		s.sync(game.getTotalDeltaTime());
	    		}
	    	} else if (game.getClient() != null) {
	    		game.getClient().sendMsg();
	    		game.getClient().sync(game.getTotalDeltaTime());
	    	}
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
    
    public void setGame (GameState game) {
    	this.game = game;
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

    
}