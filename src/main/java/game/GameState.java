package game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.levels.Level1;
import game.levels.Level2;

public class GameState {
	
	private boolean gameOver;
	private Player player1;
	private Player player2;
	private boolean levelFinished;
	private ArrayList<GameObjects> allSprites;
	private ArrayList<GameObjects> waitingSprites;
	private ArrayList<GameObjects> waitingRemovalSprites;
	private ArrayList<Platform> allPlatforms;
	private SpriteBatch batch;
    private BitmapFont font;
	
	public GameState(int gameLevel) {
		levelFinished = false;
		allSprites = new ArrayList<GameObjects>();
		waitingSprites = new ArrayList<GameObjects>();
		waitingRemovalSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();
	
		level(gameLevel);
		
	}

	public GameState() {
		this(1);
	}
	
	public double getDeltaTime() {
		try {
			return Gdx.graphics.getDeltaTime();
		} catch (Exception e) {
			System.out.print(e);
			return 0.01666667; 
		}
	}
	
	public void setBatchAndFont(SpriteBatch batch, BitmapFont font) {
		this.batch = batch;
		this.font = font;
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public BitmapFont getFont() {
		return font;
	}
 
	//Adds the sprite in waitlist to be added to the main list when allowed, avoids ConcurrentModificationException.
	public void addSprite (GameObjects s) {
		waitingSprites.add(s);
	}
	
	public void killSprite (GameObjects s) {
		waitingRemovalSprites.add(s);
	}
	
	public void addAllNewSprites(){
		for (GameObjects o : waitingSprites) {
			if (!allSprites.contains(o)){
				addSpriteQ(o);
			}
		}
		waitingSprites.clear();
	}
	
	public void removeAllDeadSprites(){
		for (GameObjects o : waitingRemovalSprites) {
			killSpriteQ(o);
		}
		waitingRemovalSprites.clear();
	}
	
	public void addSpriteQ(GameObjects s) {
		allSprites.add(s);
		if (s.getType() == "Platform") {
			allPlatforms.add((Platform) s);
		}
		if (s.getType()=="Player") {
			Player p = (Player) s;
			if (p.getIdentity() == 2) {
				player2 = p;
			} else {
				player1 = p;
			}
		}
	}
	
	public void killSpriteQ(GameObjects s) {
		if (allSprites.contains(s)) {
			allSprites.remove(s);
			if (s.getType() == "Platform") {
				allPlatforms.remove((Platform) s);
			}
		}
	}
	
	public Player getPlayer(int identity) {
		if (identity == 2) {
			return player2;
		} else {
			return player1;
		}
		
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public ArrayList<Platform> getAllPlatforms() {
		return allPlatforms;
	}
	
	public ArrayList<GameObjects> getAllSprites() {
		return allSprites; 
	}


	public void levelComplete(int playerId) {
		if (levelFinished) {
			new Text(this, 700, 550, "Better luck next time Player "+playerId+", you finished last.");
			levelFinished = false;
		} else {
			levelFinished = true;
			new Text(this, 700, 575, "Congratulations Player "+playerId+"! You won the game!");
		}
		
			
	}
	
	//TODO find a better place for this information.
	private void level(int gameLevel) {
		if (gameLevel == 1) {
			new Level1(this);
		} else if (gameLevel == 2) {
			new Level2(this);
		}
	}
	
}
