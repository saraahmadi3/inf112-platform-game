package game;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.levels.*;
import network.*;
import gameObjects.*;

/**
 * GameState is responsible for the state of the game at all moments. it sets off the game with the correct mode and level and keeps track of all sprites
 * @author saraa
 *
 */
public class GameState {
	
	private int currentLevel;
	private Player player1;
	private Player player2;
	private boolean levelFinished;
	private boolean shouldStartNextLevel;
	private ArrayList<GameObjects> allSprites;
	private ArrayList<GameObjects> waitingSprites;
	private ArrayList<GameObjects> waitingRemovalSprites;
	private ArrayList<Platform> allPlatforms;
	private ArrayList<Player> allPlayers;
	private SpriteBatch batch;
    private BitmapFont font;
	private boolean isMultiplayer;
	private int singlePlayerID;
	private boolean gameStarted;
	private int mode;
	private String inputIP;
	private HashMap<String, Sound> jukebox; // samling av lydeffekter.
	
	private GameLoop gameLoop;
	private PosClient client;
	private PosServer server;
	private double totalDeltaTime;
	private double delayDifference;
	private static StartVariabels var;
	
	/**
	 * Creates a GameState object, given a GameLoop, an int for the game level and a starting variable. 
	 * starts a game with the correct starting variables from var
	 * @param gameLoop
	 * @param gameLevel
	 * @param var
	 */
	public GameState(GameLoop gameLoop, int gameLevel, StartVariabels var) {
		
		this.gameLoop = gameLoop;
		levelFinished = false;
		shouldStartNextLevel = false;
		allSprites = new ArrayList<GameObjects>();
		waitingSprites = new ArrayList<GameObjects>();
		waitingRemovalSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();
		allPlayers = new ArrayList<Player>();
		this.var = var;
		
		//When testing there's no var
		if (Objects.isNull(var)){
			this.mode=0;
		}else {
			this.mode=var.getMode();
			this.inputIP=var.getIP();
		}
		
		jukebox = new HashMap<String, Sound>();
		
		System.out.println("Mode: "+mode);
		
		currentLevel = gameLevel;
		
		startMode(mode);
		
	}
	/**
	 * basic constructor for GameState, with default values and no GameLoop. Primarily used for testing.
	 */
	public GameState() {
		this(null,1, var);
	}
	/**
	 * alternative constructor for GameState, which takes a GameLoop. uses gameLevel 1, and StartVariables null. Primarily used for testing.
	 * @param gameLoop
	 */
	public GameState(GameLoop gameLoop) {
		this(gameLoop, 1, var);
	}
	
	/**
	 * alternative constructor for GameState, which takes a Gamelevel. uses GameLoop null and StartVariables null. Primarily used for testing.
	 * @param gameLevel
	 */
	public GameState(int gameLevel) {
		this(null, gameLevel, var);
	}
	
	/**
	 * Starts a game given a mode from 0-6:
	 * 
	 * 0: single player game, with playerId 1
	 * 1: single player game, with playerId 2
	 * 2: multi player game, with 2 players on the same screen
	 * 3: network multiplayer. automatically tries to set up the game. if other player on the network, will automatically try to connect to same game
	 * 4: network multiplayer. Start game as a server. must wait for another client to connect.
	 * 5: network multiplayer. Start game as a client. automattically tries to connect to another server on the same network.
	 * 6: network multiplayer. Start game as a client. must connect to a server using their IP address manually.
	 * @param mode
	 */
	private void startMode(int mode) {
		if (mode == 0) {
			setSinglePlayerID(1);
			startSinglePlayer();
		} else if (mode == 1) {
			setSinglePlayerID(2);
			startSinglePlayer();
		} else if (mode == 2) {
			setMultiPlayer(true);
			if (currentLevel == 0) {
				level(0);
			} else {
				level(currentLevel);
			}
			//
		} else if (mode == 3) {
			startMultiPlayer("A");
		} else if (mode == 4) {
			startMultiPlayer("S");
		} else if (mode == 5) {
			startMultiPlayer("C");
		} else if (mode == 6) {
			startMultiPlayer("M");
		}
	}
	
	/**
	 * checks if a player can use both the arrow keys and A,S,W,D keys or not. returns false if the mode is 2 (2 players on the same screen)
	 * @return
	 */
	public boolean canUseBothKeys() {
		if (mode == 2)
			return false;
		
		return true;
	}
	
	public double getTotalDeltaTime() {
		return totalDeltaTime;
	}
	/**
	 * starts a single player game at the current level
	 */
	public void startSinglePlayer() {
		setMultiPlayer(false);
		if (currentLevel == 0) {
			level(0);
		} else {
			level(currentLevel);
		}
	}

	
	public double getDeltaTime() {
		double deltaTime = 0;
		try {
			deltaTime = (double) Gdx.graphics.getDeltaTime();
		} catch (NullPointerException e) {
			//The default value if the actual one can not be fetched is 60 fps
			deltaTime = (double) 1/60; 
		} 
		
		//To prevent weird behavior after extended pauses between frames,
		//such as when dragging the window around to move it.
		deltaTime = Math.min(deltaTime, (double) 1/12); 

		if (getMultiPlayer()) {
			deltaTime = adjustForDelay(deltaTime);
		}
		
		return deltaTime;
	}
	
	/**
	 * adjust for delay in multiplayer games with network solutions.
	 * @param deltaTime
	 * @return
	 */
	private double adjustForDelay(double deltaTime) {
		double oldDeltaTime = deltaTime;
		
		//Severe adjustment
		if (delayDifference < (double) -1) {
			System.out.println("High delay: " +delayDifference);
			deltaTime *= 2;
		} else if (delayDifference > (double) 1) {
			System.out.println("High delay: " +delayDifference);
			deltaTime *= 0.5;
		}
		
		//Small adjustment
		if (delayDifference < (double) -1/360) {
			deltaTime *= 1.2;
		} else if (delayDifference > (double) 1/360) {
			deltaTime *= 0.80;
		}
		
		delayDifference -= oldDeltaTime-deltaTime;
		totalDeltaTime += deltaTime;
		
		return deltaTime;
	}

	/**
	 * add all sprites to a list to be removed
	 */
	public void clearState() {
		//totalDeltaTime = 0;
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
	
	public GameLoop getGameLoop() {
		return gameLoop;
	}
 
	/**
	 * Adds the sprite s in waitlist to be added to the main list when allowed, avoids ConcurrentModificationException.
	 * @param s
	 */
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
		if (waitingRemovalSprites.isEmpty()) return;
		GameObjects obj = waitingRemovalSprites.get(0);
		try {
			for (GameObjects o : waitingRemovalSprites) {
				obj = o;
				killSpriteQ(obj);
			}
		} catch (Exception e) {
			killSpriteQ(obj);
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
		//System.out.println("killed: "+s);
	}
	
	public Player getPlayer(int identity) {
		if (identity == 2) {
			return player2;
		} else {
			return player1;
		}
	}
	
	/**
	 * checks if player p is alive. returns true if the player is in the AllPlayers list
	 * @param p
	 * @return
	 */
	public boolean playerIsAlive (Player p) {
		return (getAllPlayers().contains(p));
	}
	
	public Player getSinglePlayer() {
		if (getSinglePlayerID()==2) {
			return player2;
		} else {
			return player1;
		}
	}
	
	public int getCurrentLevel() {
		return currentLevel;
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

	/**
	 * adds to the score of the player if the player completes a level
	 * @param playerId
	 */
	public void levelComplete(int playerId) {
		if (server != null || client != null) {
			getPlayer(playerId).changeScoreBy(250);
			if (server != null) {
				server.levelComplete();
			} else {
				client.levelComplete();
			}
			return;
		}
		if (!levelFinished) {
			getPlayer(playerId).changeScoreBy(200);
			levelFinished = true;
		}
		getPlayer(playerId).changeScoreBy(50);
	}
	
	/**
	 * checks if the level is finished. either by having all players die, then proceed to gameover, 
	 * or having all players complete the level then proceed to the next level
	 */
	private void checkForLevelComplete() {
		if (shouldStartNextLevel) {
			shouldStartNextLevel = false;
			nextLevel();
		}
		
		if (server != null || client != null) return;
		
		if (getAllPlayers().isEmpty() && gameStarted) {
			if (levelFinished) {
				levelFinished = false;
				startNextLevel();
			} else {
				gameOver(); //When both players are dead the game is over.
				gameStarted = false;
			}
		}
	}
	
	/**
	 * moves on to the next level
	 */
	public void nextLevel() {
		currentLevel = Math.max(0, currentLevel);
		currentLevel++;
		level(currentLevel);
	}
	
	/**
	 * sets shouldStartNextLevel to true
	 */
	public void startNextLevel() {
		shouldStartNextLevel = true;
	}
	public void gameOver() {
		level(-1);
	}
	/**
	 * adds all new sprites, removes all dead sprites and checks if the level is complete. 
	 */
	public void update() {
		addAllNewSprites();
		removeAllDeadSprites();
		checkForLevelComplete();
	}


	//TODO find a better place for this information.
	public void level(int gameLevel) {
		clearState();
		gameStarted = true;
		currentLevel = gameLevel;
		if (gameLevel == -1) {
			new GameOver(this);
		} else if (gameLevel == 1) {
			new Level1(this);
		} else if (gameLevel == 2) {
			new Level2(this);
		} else if (gameLevel == 3) {
			new Level3(this);
		}else if (gameLevel == 4) {
			new Level4(this);
		} else {
			new Level0(this);
			if (server != null) {
				server.playerDied(player1);
			}
			if (client != null) {
				client.playerDied(player2);
			}
		}
	}

	public PosServer getServer() {
		return server;
	}
	
	public boolean isServer() {
		return server!=null;
	}
	
	public PosClient getClient() {
		return client;
	}
	
	public boolean isClient() {
		return client!=null;
	}
	
	/**
	 * start multiplayer game with network given a string s
	 * s=A: network multiplayer. automatically tries to set up the game. if other player on the network, will automatically try to connect to same game
	 * s=S: network multiplayer. Start game as a server. must wait for another client to connect.
	 * s=C: network multiplayer. Start game as a client. automatically tries to connect to another server on the same network.
	 * s=M: network multiplayer. Start game as a client. must connect to a server using their IP address manually.
	 * @param s
	 */
	public void startMultiPlayer(String s) {
		setSinglePlayerID(0); //This value should not be accessed anyways.
		setMultiPlayer(true); 
		// server
		if (s == "S") {
			server = new PosServer(this);
			new Text(this, 350, 350, "Server running. Waiting for client to connect...");
			try {
				new Text(this, 350, 300, "Your IP: " + InetAddress.getLocalHost());
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			//client
		} else if (s == "C") {
			try {
				client = new PosClient(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//manual
		} else if (s == "M"){
			String input = inputIP;
				if (input == null || input.trim().length() == 0) System.exit(1);
				input.trim();
				try {
					client = new PosClient(this, InetAddress.getByName(input));
				} catch (IOException e) {
					e.printStackTrace();
				}
		} else { //Should only Auto for s=="A"
			try {
				client = new PosClient(this);
			} catch (IOException e) {
				server = new PosServer(this);
				new Text(this, 350, 350, "Could not find a server.\nStarted own server and is now waiting for client to connect...");
				try {
					new Text(this, 350, 300, "Your IP: " + InetAddress.getLocalHost());
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * playes the sound from fileName
	 * @param fileName
	 */
	public void playSound(String fileName) {
		if (fileName != null && !fileName.equals("testMode")) {
			try {
				if (jukebox.containsKey(fileName)) {
					jukebox.get(fileName).play(var.getVolume());
				}
				else {
					FileHandle soundFileHandle = Gdx.files.internal("sounds/"+fileName);
					Sound sound = Gdx.audio.newSound(soundFileHandle);
					jukebox.put(fileName, sound);
					sound.play(var.getVolume());
				}  
			}
			catch (Exception e) {
				System.out.println("An error occurred whilst trying to get the sound file for the sound effects: "+e);
				
			}
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

	public void syncDelay(double difference) {
		//System.out.println(delayDifference);
		this.delayDifference = difference;
	}


}