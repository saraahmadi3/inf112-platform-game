package game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState {
	
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
		return Gdx.graphics.getDeltaTime();
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
			new Tips(this);
			new Door(this, 1050, 650, 20, 34);
			new Key(this, 950, 12);
			new Key(this, 390, 65);
			player1 = new Player(50, 15, this);
			player2 = new Player(50, 15, this, "player2.png", 2);
		
			new Platform(this, -500, -10, 1150, 20, "grass.png"); //Floor
			new Platform(this, 900, -10, 180, 20, "grass.png"); //Floor
			new Platform(this, -500, 718, 2000, 20); //Top
			new Platform(this, -10, -500, 12, 2000); //Left wall
			new Platform(this, 1078, -500, 15, 2000); //Right wall
			
			
			new MovingPlatform(this, 650, 150, 100, 8, 100, 0, 50);
			MovingPlatform moving1 = new MovingPlatform(this, 800, 250, 150, 8, 0, 150, 75);
			new MovingPlatform(this, 150, 620, 75, 8, 420, 0, 75);
			
			//TODO fix bug where player is not always grounded on diagonally moving platforms
			//new MovingPlatform(this, 400, 360, 75, 8, 250, 75, 25); 
			new MovingPlatform(this, 400, 360, 75, 8, 250, 0, 50); 
			
			new BoostPlatform(this, 30, 250, 50, 8, 2);
			new BoostPlatform(this, 1000, 50, 50, 8, 2);
			new GhostPlatform(this, 750,600,100,8,2);
			
			new Platform(this, 250,400,75,8);
			Platform platform1 = new Platform(this, 200,50,100, 8);
			
			Platform bigBlock = new Platform(this, 350,100,200,80);
			new Platform(this, 500, 45, 50, 135);
			new Platform(this, 350, 10, 105, 55);
			new MovingPlatform(this, 360, 55, 8, 60, 0, 60,10);
			new MovingPlatform(this, 535, 10, 8, 60, 0, 50,10);
			
			new Platform(this, 28,600,54,8);
			Platform doorPlatform = new Platform(this, 980,642,100,8);
	
			new Enemy(this, moving1);
			new Enemy(this, platform1);
			new Enemy(this, bigBlock);
			new Enemy(this, doorPlatform);
		}
	}
	
}
