package game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.levels.Level0;
import game.levels.Level1;
import game.levels.Level2;

public class GameState {
	
	private boolean gameOver;
	private int currentLevel;
	private Player player1;
	private Player player2;
	private StartScreen startscreen;
	private boolean levelFinished;
	private ArrayList<GameObjects> allSprites;
	private ArrayList<GameObjects> waitingSprites;
	private ArrayList<GameObjects> waitingRemovalSprites;
	private ArrayList<Platform> allPlatforms;
	private ArrayList<Player> allPlayers;
	private SpriteBatch batch;
    private BitmapFont font;
	private boolean isMultiplayer;
	private int singlePlayerID;
	private boolean testNetwork;
	
	
	public GameState(int gameLevel) {
		testNetwork = false;
		levelFinished = false;
		allSprites = new ArrayList<GameObjects>();
		waitingSprites = new ArrayList<GameObjects>();
		waitingRemovalSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();
		allPlayers = new ArrayList<Player>();
	
		currentLevel = gameLevel;
		if (gameLevel != 0) {
			startscreen = new StartScreen(this, gameLevel);
		} else {
			level(0);
		}
		
	}

	public GameState() {
		this(1);
	}
	
	public double getDeltaTime() {
		double deltaTime = 0;
		try {
			deltaTime = (double) Gdx.graphics.getDeltaTime();
		} catch (NullPointerException e) {
			return 0.01666667;  
		} 
		return deltaTime;
	}
	
	public void clearState() {
		waitingRemovalSprites.addAll(allSprites);
		waitingRemovalSprites.addAll(waitingSprites);
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
		
		if (s.getType()=="Player") {
			Player p = (Player) s;
			allPlayers.add(p);
			if (p.getIdentity() == 2) {
				player2 = p;
			} else {
				player1 = p;
			}
		}
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
		}
		if (allPlatforms.contains(s)) {
			allPlatforms.remove((Platform) s);
		}
		if (allPlayers.contains(s)) {
			allPlayers.remove((Player) s);
		}
		/*
		if (s.getType()=="Player") {
			Player p = (Player) s;
			if (p.getIdentity() == 2) {
				player2 = null;
			} else {
				player1 = null;
			}
		}
		*/
		
	}
	
	public Player getPlayer(int identity) {
		if (identity == 2) {
			return player2;
		} else {
			return player1;
		}
	}
	
	public Player getSinglePlayer() {
		if (getSinglePlayerID()==2) {
			return player2;
		} else {
			return player1;
		}
	}
	
	/*
	//TODO: Fix how this works for multiplayer, or remove completely.
	public Player getOtherPlayer(int identity) {
		if (identity == 1) {
			return player2;
		} else {
			return player1;
		}
	}
	*/
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean gameover) {
		gameOver = gameover;
	}
	
	public ArrayList<Platform> getAllPlatforms() {
		return allPlatforms;
	}
	
	public ArrayList<GameObjects> getAllSprites() {
		return allSprites; 
	}
	
	public ArrayList<Player> getAllPlayers() {
		return allPlayers; 
	}


	public void levelComplete(int playerId) {
		if (!levelFinished) {
			getPlayer(playerId).changeScoreBy(200);
			levelFinished = true;
		}
		getPlayer(playerId).changeScoreBy(50);
	}
	
	private void checkForLevelComplete() {
		if (getAllPlayers().isEmpty() && !getAllSprites().contains(startscreen)) {
			if (levelFinished) {
				levelFinished = false;
				currentLevel++;
				level(currentLevel);
			} else {
				gameOver=true; //When both players are dead the game is over.
			}
		}
	}
	
	//TODO: This should only be called once, not every render() call
	public void gameOver() {
		setGameOver(true);
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        getBatch().begin();
		if (!getMultiPlayer()) {
	    	
			new Text(this, 700, 575, "Game over. You got " + this.getSinglePlayerID() + " points.").draw();;
		}
		else {
			int Player1Points = getPlayer(1).getScore();
			int Player2Points = getPlayer(2).getScore();
			new Text(this, 700, 575, "Game over. Player 1  got " + Player1Points + ".").draw();;
			new Text(this, 700, 475, "Game over. Player 2  got " + Player2Points + ".").draw();;
			if (Player1Points > Player2Points) {
				new Text(this, 700, 375, "Player 1 won!").draw();;
			}
			else if (Player1Points < Player2Points) {
				new Text(this, 700, 375, "Player 2 won!").draw();;
			}
			else {
				new Text(this, 700, 275, "It's a draw.").draw();;
			}
			
		}
		getBatch().end();
	}
	
	public void update() {
		addAllNewSprites();
		removeAllDeadSprites();
		checkForLevelComplete();
	}


	//TODO find a better place for this information.
	public void level(int gameLevel) {
		clearState();
		if (gameLevel == 1) {
			new Level1(this);
		} else if (gameLevel == 2) {
			new Level2(this);
		} else {
			new Level0(this);
		}
		
		if (testNetwork==true) {
	        new Network();
	        new PosClient(this);
	        new PosServer(this);
		}
	}

	public void setMultiPlayer(boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
	}
	
	public boolean getMultiPlayer() {
		return isMultiplayer;
	}
	
	public int getSinglePlayerID() {
		return singlePlayerID;
	}
	
	public void setSinglePlayerID(int playerID) {
		singlePlayerID = playerID;
	}
}
