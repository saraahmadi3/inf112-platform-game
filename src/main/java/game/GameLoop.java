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
    private StartVariabels var;
	private Music music;

    private boolean isSynced;
    
    public GameLoop(StartVariabels var) {
    	this.var=var;
    }
    
    @Override
    public void create() { 
 
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
     
        isSynced = false;
        
        //Musikken som spilles i bakgrunnen
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Sad_Creation.ogg"));
        music.setVolume(var.getVolume()/2);
        music.setLooping(true);
        music.play();
        
        game = new GameState (this, 1, var);
        game.setBatchAndFont(batch, font);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        music.dispose(); 
    }
    
    //Draws a blank screen, used as a reset between each frame
    public static void clearScreen() {
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
    


    @Override
    public void render() {
    	if (game == null) return;
    	
        clearScreen();
        game.update();
        updateAll();
        drawAll();  
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

    //Below methods must be implemented from ApplicationListener, but for now we don't have much use for them.
	@Override
	public void resize(int width, int height) {
		return;	
	}
	@Override
	public void pause() {
		return;
	}
	@Override
	public void resume() {
		return;	
	}


    
}
