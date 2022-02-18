package game;

import java.util.ArrayList;

public class GameState {
	
	private ArrayList<GameObjects> allSprites;
	private ArrayList<Platform> allPlatforms;
	
	public GameState() {
		allSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();
		
		new Tips(this);
		new Player(50,15, this);
	
		new Platform(this, -500, -10, 2000, 20, "grass.png"); //Floor
		new Platform(this, -500, 718, 2000, 20); //Top
		new Platform(this, -10, -500, 12, 2000); //Left wall
		new Platform(this, 1078, -500, 15, 2000); //Right wall
		
		new Platform(this, 300,50,200,8);
		new Platform(this, 550,30,100,16);
		
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
	
	public ArrayList<Platform> getAllPlatforms() {
		return allPlatforms;
	}
	
	public ArrayList<GameObjects> getAllSprites() {
		return allSprites; 
	}
}
