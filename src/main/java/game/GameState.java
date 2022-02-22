package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player1;
	private ArrayList<GameObjects> allSprites;
	private ArrayList<GameObjects> waitingSprites;
	private ArrayList<GameObjects> waitingRemovalSprites;
	private ArrayList<Platform> allPlatforms;
	
	public GameState() {
		allSprites = new ArrayList<GameObjects>();
		waitingSprites = new ArrayList<GameObjects>();
		waitingRemovalSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();

		level1();
		
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
			addSpriteQ(o);
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
		allSprites.remove(s);
		if (s.getType() == "Platform") {
			allPlatforms.remove((Platform) s);
		}
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public ArrayList<Platform> getAllPlatforms() {
		return allPlatforms;
	}
	
	public ArrayList<GameObjects> getAllSprites() {
		return allSprites; 
	}


	public void levelComplete() {
		new Text(this, 800, 600, "Level complete. TODO: Make next level");
	}
	
	//TODO find a better place for this information.
	private void level1() {
		new Tips(this);
		new Door(this, 1050, 650, 20, 34);
		new Key(this, 1050, 12);
		player1 = new Player(50, 15, this);
	
		new Platform(this, -500, -10, 2000, 20, "grass.png"); //Floor
		new Platform(this, -500, 718, 2000, 20); //Top
		new Platform(this, -10, -500, 12, 2000); //Left wall
		new Platform(this, 1078, -500, 15, 2000); //Right wall
		
		
		new MovingPlatform(this, 600, 150, 100, 8, 100, 0, 50);
		new MovingPlatform(this, 800, 250, 150, 8, 0, 150, 75);
		new MovingPlatform(this, 150, 620, 75, 8, 420, 0, 75);
		
		//TODO fix bug where player is not always grounded on diagonally moving platforms
		//new MovingPlatform(this, 400, 360, 75, 8, 250, 75, 25); 
		new MovingPlatform(this, 400, 360, 75, 8, 250, 0, 50); 
		
		new BoostPlatform(this, 30, 250, 50, 8, 2);
		new GhostPlatform(this, 750,600,100,8,2);
		
		new Platform(this, 250,400,75,8);
		new Platform(this, 200,50,100, 8);
		new Platform(this, 350,10,200,170);
		new Platform(this, 28,600,54,8);
		new Platform(this, 980,642,500,8);
	}
	
}
