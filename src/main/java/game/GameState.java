package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player1;
	private ArrayList<GameObjects> allSprites;
	private ArrayList<Platform> allPlatforms;
	
	public GameState() {
		allSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();

		
		new Tips(this);
		player1 = new Player(50, 15, this);
	
		new Platform(this, -500, -10, 2000, 20, "grass.png"); //Floor
		new Platform(this, -500, 718, 2000, 20); //Top
		new Platform(this, -10, -500, 12, 2000); //Left wall
		new Platform(this, 1078, -500, 15, 2000); //Right wall
		
		
		new MovingPlatform(this, 600, 150, 100, 8, 100, 0, 50);
		new MovingPlatform(this, 800, 250, 150, 8, 0, 200, 75);
		
		//TODO fix bug where player is not always grounded on diagonally moving platforms
		new MovingPlatform(this, 400, 360, 75, 8, 250, 75, 25); 
		
		new Platform(this, 250,400,75,8);
		new Platform(this, 200,50,100,8);
		new Platform(this, 350,10,200,170);
		
	}
	
	
	public void addSprite(GameObjects s) {
		allSprites.add(s);
		if (s.getSymbol() == "##################") {
			allPlatforms.add((Platform) s);
		}
	}
	
	public void killSprite(GameObjects s) {
		allSprites.remove(s);
		if (s.getSymbol() == "##################") {
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
}
